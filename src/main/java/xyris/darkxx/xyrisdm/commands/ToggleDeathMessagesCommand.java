package xyris.darkxx.xyrisdm.commands;

import org.bukkit.ChatColor;
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

        plugin.getCommand("toggldeathmessages").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("toggldeathmessages")) {
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

                if (!currentStatus) {
                    String enabledMessage = messagesManager.getDeathMessagesEnabledMessage();
                    player.sendMessage(enabledMessage);
                } else {
                    String disabledMessage = messagesManager.getDeathMessagesDisabledMessage();
                    player.sendMessage(disabledMessage);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            }
            return true;
        }
        return false;
    }

    public boolean isDeathMessagesEnabled(UUID playerId) {
        return deathMessagesConfig.getBoolean(playerId.toString(), true);
    }
}
