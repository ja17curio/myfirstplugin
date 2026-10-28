package games.toost.myfirstplugin.commands.misc;

import games.toost.myfirstplugin.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("myfirstplugin.heal")) {
                player.setHealth(20);
                player.sendMessage("Je bent gehealed!");
            } else {
                Messages.noPermission(player);
            }
            return true;
        }
        return false;
    }
}
