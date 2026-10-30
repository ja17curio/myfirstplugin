package games.toost.myfirstplugin.commands.teleports;

import games.toost.myfirstplugin.Messages;
import games.toost.myfirstplugin.helpers.PermissionHelper;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TeleportCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {

            if (!PermissionHelper.checkPermission("myfirstplugin.delwarp", player)) {
                Messages.noPermission(player);
                return true;
            }

            if (args.length == 3) {
                double x;
                double y;
                double z;
                try {
                    x = Double.parseDouble(args[0]);
                    y = Double.parseDouble(args[1]);
                    z = Double.parseDouble(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage("De coördinaten moeten getallen zijn!");
                    return true;
                }

                World playerWorld = player.getLocation().getWorld();
                Location teleportLocation = new Location(playerWorld, x, y, z);
                player.teleport(teleportLocation);
                player.sendMessage("Je bent geteleporteerd naar: " + x + " " + y + " " + z + " in de dimensie: " + playerWorld.getKey().getKey());

                return true;
            } else {
                player.sendMessage("Gebruik: /teleport <x> <y> <z>");
                return true;
            }

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return List.of();
        }
        Location loc = player.getLocation();
        // args.length vertelt aan welk argument de speler nu typt
        switch (args.length) {
            case 1:
                return List.of(String.valueOf(loc.getBlockX()));
            case 2:
                return List.of(String.valueOf(loc.getBlockY()));
            case 3:
                return List.of(String.valueOf(loc.getBlockZ()));
            default:
                return List.of(); // na 3 argumenten: geen suggesties
        }
    }
}
