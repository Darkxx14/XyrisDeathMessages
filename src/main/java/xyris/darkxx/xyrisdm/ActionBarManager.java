package xyris.darkxx.xyrisdm;

import org.bukkit.entity.Player;
public class ActionBarManager {
    private final Main plugin;
    public ActionBarManager(Main plugin) {
        this.plugin = plugin;
    }
    public void sendKillActionBar(Player attacker, Player victim) {
        if (plugin.getConfig().getBoolean("kill-action-bar.enabled")) {
            String victimName = victim != null ? victim.getName() : "?";
            String victimHealth = victim != null ? String.format("%.2f", victim.getHealth()) : "0.00";
            String attackerName = attacker != null ? attacker.getName() : "?";
            String attackerHealth = attacker != null ? String.format("%.2f", attacker.getHealth()) : "0.00";
            String actionBarMessage = plugin.getConfig().getString("kill-action-bar.message")
                    .replace("%victim%", victimName)
                    .replace("%attacker%", attackerName)
                    .replace("%victim_health%", victimHealth)
                    .replace("%attacker_health%", attackerHealth);
            String formattedActionBarMessage = plugin.formatColors(actionBarMessage);
            sendActionBar(attacker, formattedActionBarMessage);
        }
    }
    public void sendDeathActionBar(Player victim, Player attacker) {
        if (plugin.getConfig().getBoolean("death-action-bar.enabled")) {
            String attackerName = attacker != null ? attacker.getName() : "?";
            String attackerHealth = attacker != null ? String.format("%.2f", attacker.getHealth()) : "0.00";
            String victimName = victim != null ? victim.getName() : "?";
            String victimHealth = victim != null ? String.format("%.2f", victim.getHealth()) : "0.00";
            String actionBarMessage = plugin.getConfig().getString("death-action-bar.message")
                    .replace("%victim%", victimName)
                    .replace("%attacker%", attackerName)
                    .replace("%victim_health%", victimHealth)
                    .replace("%attacker_health%", attackerHealth);
            String formattedActionBarMessage = plugin.formatColors(actionBarMessage);
            sendActionBar(victim, formattedActionBarMessage);
        }
    }
    private void sendActionBar(Player player, String message) {
        if (player != null) {
            player.sendActionBar(message);
        }
    }
}
