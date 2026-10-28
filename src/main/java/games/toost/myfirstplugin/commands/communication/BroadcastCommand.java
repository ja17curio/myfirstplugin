package games.toost.myfirstplugin.commands.communication;

import games.toost.myfirstplugin.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("myfirstplugin.broadcast")) {
                if (args.length == 0) {
                    player.sendMessage("Gebruik /broadcast <message>");
                } else {
                    String message = String.join(" ", args);
                    Bukkit.broadcast(Component.text("§1" + message));
                    return true;
                }
            } else {
                Messages.noPermission(player);
                return true;
            }
        }
        return false;
    }
}
