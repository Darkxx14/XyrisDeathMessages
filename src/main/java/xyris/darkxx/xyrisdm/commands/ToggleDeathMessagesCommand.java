package xyris.darkxx.xyrisdm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import xyris.darkxx.xyrisdm.Main;
import xyris.darkxx.xyrisdm.MessagesManager;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ToggleDeathMessagesCommand implements CommandExecutor {
    private final Main plugin;
    private final MessagesManager messagesManager;
    private final File deathMessagesFile;
    private FileConfiguration deathMessagesConfig;
    public ToggleDeathMessagesCommand(Main plugin) {
        this.plugin = plugin;
        this.messagesManager = new MessagesManager(plugin);

        this.deathMessagesFile = new File(plugin.getDataFolder(), "death-messages-data.yml");
        this.deathMessagesConfig = YamlConfiguration.loadConfiguration(deathMessagesFile);

        plugin.getCommand("toggledeathmessages").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("toggledeathmessages")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                UUID playerId = player.getUniqueId();
                boolean currentStatus = isDeathMessagesEnabled(playerId);

                deathMessagesConfig.set(playerId.toString(), !currentStatus);
                try {
                    deathMessagesConfig.save(deathMessagesFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String message;
                if (!currentStatus) {
                    message = messagesManager.getDeathMessagesEnabledMessage();
                } else {
                    message = messagesManager.getDeathMessagesDisabledMessage();
                }
                player.sendMessage(message);
            } else {
                sender.sendMessage(formatColors("&cYou're not allowed to use this command."));
            }
            return true;
        }
        return false;
    }
    public static String formatColors(String message) {
        message = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String colorCode = matcher.group();
            net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.of(colorCode.substring(1));
            message = message.replace(colorCode, color.toString());
        }
        return message;
    }
    public boolean isDeathMessagesEnabled(UUID playerId) {
        return deathMessagesConfig.getBoolean(playerId.toString(), true);
    }
}