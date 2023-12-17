package xyris.darkxx.xyrisdm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import xyris.darkxx.xyrisdm.Main;
import xyris.darkxx.xyrisdm.MessagesManager;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {

    private final Main mainInstance;
    private final MessagesManager messagesManager;

    public ReloadCommand(Main mainInstance, MessagesManager messagesManager) {
        this.mainInstance = mainInstance;
        this.messagesManager = new MessagesManager(mainInstance);

        mainInstance.getCommand("xyrisdm").setExecutor(this);
        mainInstance.getCommand("xyrisdm").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("xyrisdm") && args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("xyrisdm.reload")) {
                reloadConfigs(sender);
                return true;
            } else {
                sender.sendMessage("§cYou don't have permission to use this command!");
            }
        }
        return false;
    }

    private void reloadConfigs(CommandSender sender) {
        mainInstance.reloadPluginConfig();
        messagesManager.reloadMessagesConfig();
        sender.sendMessage("§aDone! Config reloaded, messages.yml will not be reloaded restart the server to apply the changes.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1 && "reload".startsWith(args[0].toLowerCase())) {
            completions.add("reload");
        }
        return completions;
    }
}
