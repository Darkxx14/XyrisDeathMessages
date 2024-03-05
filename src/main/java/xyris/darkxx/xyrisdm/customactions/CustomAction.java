package xyris.darkxx.xyrisdm.customactions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
public class CustomAction {
    private final ActionType type;
    private final String parameter;
    public CustomAction(ActionType type, String parameter) {
        this.type = type;
        this.parameter = parameter;
    }
    public void execute(Player attacker, Player victim) {
        String attackerName = (attacker != null) ? attacker.getName() : "?";
        String victimName = (victim != null) ? victim.getName() : "?";

        String formattedParameter = parameter.replace("%attacker%", attackerName).replace("%victim%", victimName);

        switch (type) {
            case CONSOLE_COMMAND:
                String formattedCommand = formattedParameter.trim();
                formattedCommand = formattedCommand
                        .replace("{attacker}", attackerName)
                        .replace("{victim}", victimName);
                formattedCommand = formattedCommand.replaceAll("^\"|\"$", "");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), formattedCommand);
                break;
            case ATTACKER_COMMAND:
                if (attacker != null) {
                    String formattedAttackerCommand = formattedParameter.trim();
                    formattedAttackerCommand = formattedAttackerCommand
                            .replace("{attacker}", attackerName)
                            .replace("{victim}", victimName);
                    formattedAttackerCommand = formattedAttackerCommand.replaceAll("^\"|\"$", "");
                    attacker.performCommand(formattedAttackerCommand);
                }
                break;
            case VICTIM_COMMAND:
                if (victim != null) {
                    String formattedVictimCommand = formattedParameter.trim();
                    formattedVictimCommand = formattedVictimCommand
                            .replace("{attacker}", attackerName)
                            .replace("{victim}", victimName);
                    formattedVictimCommand = formattedVictimCommand.replaceAll("^\"|\"$", "");
                    victim.performCommand(formattedVictimCommand);
                }
                break;
        }
    }
}