package games.toost.myfirstplugin.commands.warps;

import games.toost.myfirstplugin.Messages;
import games.toost.myfirstplugin.Myfirstplugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetwarpCommand implements CommandExecutor {

    FileConfiguration config;
    Myfirstplugin plugin;

    public SetwarpCommand(FileConfiguration warpConfig, Myfirstplugin plugin) {
        config = warpConfig;
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("myfirstplugin.setwarp")) {
                if (args.length == 1) {
                    String warpName = args[0];
                    String warpPath = "warp." + warpName;

                    if (!config.contains(warpPath)) {
                        Location loc = player.getLocation();
                        config.set(warpPath, loc);
                        plugin.saveConfig(config, "warps.yml");
                        player.sendMessage("warp: " + warpName + " aangemaakt.");
                    } else {
                        player.sendMessage("§cWarp bestaat al");
                    }
                } else {
                    player.sendMessage("Je moet alleen de naam mee geven voor het opslaan van een warp.");
                }

            } else {
                Messages.noPermission(player);
            }
            return true;
        }
        return false;
    }
}
