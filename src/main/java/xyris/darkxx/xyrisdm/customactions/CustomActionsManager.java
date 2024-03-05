package xyris.darkxx.xyrisdm.customactions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
public class CustomActionsManager {
    private final JavaPlugin plugin;
    private final List<CustomAction> actions;
    public CustomActionsManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.actions = new ArrayList<>();
        loadActions();
    }
    private void loadActions() {
        File actionsFile = new File(plugin.getDataFolder(), "actions.xk");
        if (!actionsFile.exists()) {
            plugin.saveResource("actions.xk", false);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(actionsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("on-death")) {
                    continue;
                }
                processActionLine(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reloadActions() {
        loadActions();
    }
    private void processActionLine(String line) {
        String[] parts = line.split("\\s+", 2);
        if (parts.length != 2) {
            return;
        }
        String actionTypeStr = parts[0].toUpperCase();
        String parameter = parts[1];

        ActionType actionType;
        try {
            actionType = ActionType.valueOf(actionTypeStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid action type " + actionTypeStr);
            return;
        }
        actions.add(new CustomAction(actionType, parameter));
    }
    public void executeActions(Player attacker, Player victim) {
        for (CustomAction action : actions) {
            action.execute(attacker, victim);
        }
    }
}
