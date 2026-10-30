package games.toost.myfirstplugin.commands.communication;

import games.toost.myfirstplugin.Messages;
import games.toost.myfirstplugin.helpers.PermissionHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {

            if (!PermissionHelper.checkPermission("myfirstplugin.broadcast", player)) {
                Messages.noPermission(player);
                return true;
            }

            if (args.length == 0) {
                player.sendMessage("Gebruik /broadcast <message>");
            } else {
                String message = String.join(" ", args);
                Bukkit.broadcast(Component.text("§1" + message));
            }
            return true;
        }
        return false;
    }
}
