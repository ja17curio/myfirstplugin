package games.toost.myfirstplugin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player) {
            World playerWorld = player.getLocation().getWorld();
            int y = playerWorld.getHighestBlockYAt(0,0) + 1;
            Location teleportLocation = new Location(playerWorld, 0, y,0);
            player.teleport(teleportLocation);
            player.sendMessage("Je bent geteleporteerd naar spawn");
        }

        return false;
    }
}
