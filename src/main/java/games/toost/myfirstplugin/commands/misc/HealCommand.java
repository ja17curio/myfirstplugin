package games.toost.myfirstplugin.commands.misc;

import games.toost.myfirstplugin.Messages;
import games.toost.myfirstplugin.helpers.PermissionHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {

            if (!PermissionHelper.checkPermission("myfirstplugin.heal", player)) {
                Messages.noPermission(player);
                return true;
            }

            player.setHealth(20);
            player.sendMessage("Je bent gehealed!");

            return true;
        }
        return false;
    }
}
