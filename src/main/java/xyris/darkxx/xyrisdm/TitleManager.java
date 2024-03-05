package xyris.darkxx.xyrisdm;

import org.bukkit.entity.Player;
public class TitleManager {
    private final Main plugin;
    public TitleManager(Main plugin) {
        this.plugin = plugin;
    }
    public void sendKillTitles(Player attacker, Player victim) {
        if (plugin.getConfig().getBoolean("kill-titles.enabled")) {
            String titleMessage = plugin.getConfig().getString("kill-titles.kill-title");
            String subtitleMessage = plugin.getConfig().getString("kill-titles.kill-subtitle");
            titleMessage = titleMessage.replace("%attacker%", attacker != null ? attacker.getName() : "?");
            titleMessage = titleMessage.replace("%victim%", victim != null ? victim.getName() : "?");
            subtitleMessage = subtitleMessage.replace("%attacker%", attacker != null ? attacker.getName() : "?");
            subtitleMessage = subtitleMessage.replace("%victim%", victim != null ? victim.getName() : "?");
            String formattedTitleMessage = plugin.formatColors(titleMessage);
            String formattedSubtitleMessage = plugin.formatColors(subtitleMessage);
            sendTitle(attacker, formattedTitleMessage, formattedSubtitleMessage);}}
    public void sendDeathTitles(Player victim, Player attacker) {
        if (plugin.getConfig().getBoolean("death-titles.enabled")) {
            String titleMessage = plugin.getConfig().getString("death-titles.death-title");
            String subtitleMessage = plugin.getConfig().getString("death-titles.death-subtitle");
            titleMessage = titleMessage.replace("%attacker%", attacker != null ? attacker.getName() : "?");
            titleMessage = titleMessage.replace("%victim%", victim != null ? victim.getName() : "?");
            subtitleMessage = subtitleMessage.replace("%attacker%", attacker != null ? attacker.getName() : "?");
            subtitleMessage = subtitleMessage.replace("%victim%", victim != null ? victim.getName() : "?");
            String formattedTitleMessage = plugin.formatColors(titleMessage);
            String formattedSubtitleMessage = plugin.formatColors(subtitleMessage);
            sendTitle(victim, formattedTitleMessage, formattedSubtitleMessage);}}
    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 40, 10);}}