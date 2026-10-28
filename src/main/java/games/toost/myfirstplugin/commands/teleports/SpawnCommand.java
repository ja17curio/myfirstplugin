package games.toost.myfirstplugin.commands.teleports;

import games.toost.myfirstplugin.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("myfirstplugin.spawn")) {
                int y = Bukkit.getWorlds().getFirst().getHighestBlockYAt(0, 0) + 1;
                Location teleportLocation = new Location(Bukkit.getWorlds().getFirst(), 0, y, 0);
                player.teleport(teleportLocation);
                player.sendMessage("Je bent geteleporteerd naar spawn");
            } else {
                Messages.noPermission(player);
            }
            return true;
        }

        return false;
    }
}
