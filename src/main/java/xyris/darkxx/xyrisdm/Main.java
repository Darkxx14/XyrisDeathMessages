package xyris.darkxx.xyrisdm;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyris.darkxx.xyrisdm.commands.ReloadCommand;
import xyris.darkxx.xyrisdm.commands.ToggleDeathMessagesCommand;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {
    private ToggleDeathMessagesCommand toggleDeathMessagesCommand;

    private FileConfiguration config;
    private MessagesManager messagesManager;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        this.messagesManager = new MessagesManager(this);
        getServer().getPluginManager().registerEvents(this, this);
        ReloadCommand reloadCommand = new ReloadCommand(this, messagesManager);
        toggleDeathMessagesCommand = new ToggleDeathMessagesCommand(this);
    }

    private void loadConfig() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player attacker = event.getEntity().getKiller();

        if (!isDeathMessagesEnabled(victim.getUniqueId())) {
            event.setDeathMessage(null);
            return;
        }

        List<String> deathMessages = config.getStringList("death_messages.custom");
        if (!deathMessages.isEmpty()) {
            String deathMessage = getRandomDeathMessage(deathMessages)
                    .replace("%victim%", victim.getName())
                    .replace("%attacker%", attacker != null ? attacker.getName() : "NoPlayer");

            double attackerHealth = attacker != null ? attacker.getHealth() : 0.0;
            deathMessage = deathMessage.replace("%attacker_health%", String.format("%.2f", attackerHealth));

            broadcastDeathMessage(deathMessage);
            event.setDeathMessage(null);
        }
    }

    private boolean isDeathMessagesEnabled(UUID playerId) {
        return toggleDeathMessagesCommand.isDeathMessagesEnabled(playerId);
    }
    private String getRandomDeathMessage(List<String> deathMessages) {
        int randomIndex = new Random().nextInt(deathMessages.size());
        return ChatColor.translateAlternateColorCodes('&', deathMessages.get(randomIndex));
    }

    private void broadcastDeathMessage(String deathMessage) {
        getServer().broadcastMessage(deathMessage);
    }


    public void reloadPluginConfig() {
        reloadConfig();
        config = getConfig();
    }
}