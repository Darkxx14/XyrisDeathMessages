package xyris.darkxx.xyrisdm;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class MessagesManager {

    private final JavaPlugin plugin;
    private FileConfiguration messagesConfig;
    private final File messagesFile;

    public MessagesManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        reloadMessagesConfig();
    }

    public String getDeathMessagesEnabledMessage() {
        return ChatColor.translateAlternateColorCodes('&', messagesConfig.getString("deathMessagesEnabled"));
    }

    public String getDeathMessagesDisabledMessage() {
        return ChatColor.translateAlternateColorCodes('&', messagesConfig.getString("deathMessagesDisabled"));
    }

    public void setDeathMessagesEnabledMessage(String message) {
        messagesConfig.set("deathMessagesEnabled", message);
        saveMessagesConfig();
    }

    public void setDeathMessagesDisabledMessage(String message) {
        messagesConfig.set("deathMessagesDisabled", message);
        saveMessagesConfig();
    }

    public void reloadMessagesConfig() {
        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        this.messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
    public FileConfiguration getMessagesConfig() {
        return this.messagesConfig;
    }
    private void saveMessagesConfig() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
