package games.toost.myfirstplugin.commands.warps;


import games.toost.myfirstplugin.Messages;
import games.toost.myfirstplugin.Myfirstplugin;
import games.toost.myfirstplugin.helpers.ConfigHelper;
import games.toost.myfirstplugin.helpers.PermissionHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DelwarpCommand implements TabExecutor {

    FileConfiguration config;
    Myfirstplugin plugin;

    public DelwarpCommand(FileConfiguration warpConfig, Myfirstplugin plugin) {
        this.config = warpConfig;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player) {

            if (!PermissionHelper.checkPermission("myfirstplugin.delwarp", player)) {
                Messages.noPermission(player);
                return true;
            }

            if (args.length == 1) {
                String warpName = args[0];
                String warpPath = "warp." + warpName;

                if (!config.contains(warpPath)) {
                    player.sendMessage("§cWarp bestaat niet");
                } else {
                    ConfigHelper.saveConfig(config, plugin, "warps.yml", null, warpPath);
                    player.sendMessage("Warp " + warpName + " verwijderd");
                }

            } else {
                player.sendMessage("Kies een warp om te verwijderen");
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            suggestions = ConfigHelper.getConfigKeys("warp", args[0], config);
        }

        return suggestions;
    }
}
