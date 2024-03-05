package xyris.darkxx.xyrisdm.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class HealOnKill implements Listener {
    private JavaPlugin plugin;
    private boolean healOnKill;
    public HealOnKill(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        loadConfig();
    }
    private void loadConfig() {
        healOnKill = plugin.getConfig().getBoolean("heal_on_kill", false);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player attacker = event.getEntity().getKiller();

        if (attacker != null && healOnKill) {
            healAttacker(attacker);
        }
    }
    private void healAttacker(Player attacker) {
        double attackerHealth = attacker.getHealth();
        double maxHealth = attacker.getMaxHealth();
        double newHealth = Math.min(attackerHealth + 10, maxHealth);
        attacker.setHealth(newHealth);
    }
}
