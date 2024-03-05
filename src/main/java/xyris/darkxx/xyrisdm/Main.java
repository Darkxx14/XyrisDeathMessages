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
import xyris.darkxx.xyrisdm.utils.HealOnKill;
import xyris.darkxx.xyrisdm.customactions.CustomActionsManager;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main extends JavaPlugin implements Listener {
    private ToggleDeathMessagesCommand toggleDeathMessagesCommand;
    private FileConfiguration config;
    private MessagesManager messagesManager;
    private TitleManager titleManager;
    private ActionBarManager actionBarManager;
    private HealOnKill healonkill;
    private CustomActionsManager customActionsManager;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        this.messagesManager = new MessagesManager(this);
        getServer().getPluginManager().registerEvents(this, this);
        ReloadCommand reloadCommand = new ReloadCommand(this, messagesManager);
        toggleDeathMessagesCommand = new ToggleDeathMessagesCommand(this);
        titleManager = new TitleManager(this);
        this.actionBarManager = new ActionBarManager(this);
        healonkill = new HealOnKill(this);
        customActionsManager = new CustomActionsManager(this);}
    private void loadConfig() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();}
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player attacker = event.getEntity().getKiller();
        if (!isDeathMessagesEnabled(victim.getUniqueId())) {
            event.setDeathMessage(null);
            return;}
        if (attacker == null || !(attacker instanceof Player)) {
            attacker = null;}
        if (attacker != null) {
            titleManager.sendKillTitles((Player) attacker, victim);
            actionBarManager.sendKillActionBar((Player) attacker, victim);}
         titleManager.sendDeathTitles(victim, attacker);
        actionBarManager.sendDeathActionBar(victim, attacker);
        List<String> deathMessages = config.getStringList("death_messages.custom");
        if (!deathMessages.isEmpty()) {
            String victimName = victim.getName();
            String attackerName = (attacker != null) ? attacker.getName() : "?"; // Replace attacker's name with "?" if null
            double attackerHealth = (attacker != null) ? attacker.getHealth() : 0.0;

            String deathMessage = getRandomDeathMessage(deathMessages)
                    .replace("%victim%", victimName)
                    .replace("%attacker%", attackerName);
            deathMessage = deathMessage.replace("%attacker_health%", String.format("%.2f", attackerHealth));
            deathMessage = formatColors(deathMessage);
            broadcastDeathMessage(deathMessage);
            event.setDeathMessage(null);}
        customActionsManager.executeActions(attacker, victim);}
    private boolean isDeathMessagesEnabled(UUID playerId) {
        return toggleDeathMessagesCommand.isDeathMessagesEnabled(playerId);}
    private String getRandomDeathMessage(List<String> deathMessages) {
        int randomIndex = new Random().nextInt(deathMessages.size());
        return ChatColor.translateAlternateColorCodes('&', deathMessages.get(randomIndex));}
    private void broadcastDeathMessage(String deathMessage) {
        getServer().broadcastMessage(deathMessage);
    }
    public void reloadPluginConfig() {
        reloadConfig();
        customActionsManager.reloadActions();
        config = getConfig();}
    public static String formatColors(String message) {
        message = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String colorCode = matcher.group();
            net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.of(colorCode.substring(1));
            message = message.replace(colorCode, color.toString());}
        return message;}}
