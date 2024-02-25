package xyris.darkxx.xyrisdm;

import org.bukkit.entity.Player;
public class TitleManager {
    private final Main plugin;

    public TitleManager(Main plugin) {
        this.plugin = plugin;
    }
    public void sendKillTitles(Player player, Player victim) {
        if (this.plugin.getConfig().getBoolean("kill-titles.enabled")) {
            String titleMessage = this.plugin.getConfig().getString("kill-titles.kill-title");
            String subtitleMessage = this.plugin.getConfig().getString("kill-titles.kill-subtitle");
            titleMessage = replacePlaceholders(titleMessage, player, victim);
            subtitleMessage = replacePlaceholders(subtitleMessage, player, victim);
            String formattedTitleMessage = Main.formatColors(titleMessage);
            String formattedSubtitleMessage = Main.formatColors(subtitleMessage);
            this.sendTitle(player, formattedTitleMessage, formattedSubtitleMessage);
        }
    }
    public void sendDeathTitles(Player player, Player attacker) {
        if (this.plugin.getConfig().getBoolean("death-titles.enabled")) {
            String titleMessage = this.plugin.getConfig().getString("death-titles.death-title");
            String subtitleMessage = this.plugin.getConfig().getString("death-titles.death-subtitle");
            titleMessage = replacePlaceholders(titleMessage, player, attacker);
            subtitleMessage = replacePlaceholders(subtitleMessage, player, attacker);
            String formattedTitleMessage = Main.formatColors(titleMessage);
            String formattedSubtitleMessage = Main.formatColors(subtitleMessage);
            this.sendTitle(player, formattedTitleMessage, formattedSubtitleMessage);
        }
    }
    private String replacePlaceholders(String message, Player attacker, Player victim) {
        if (message != null) {
            message = message.replace("%attacker%", attacker != null ? attacker.getName() : "?");
            message = message.replace("%victim%", victim != null ? victim.getName() : "?");
        }
        return message;
    }
    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 40, 10);
    }
}