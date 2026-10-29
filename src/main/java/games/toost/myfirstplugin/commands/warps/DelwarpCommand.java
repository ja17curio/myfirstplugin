package games.toost.myfirstplugin.commands.warps;

import games.toost.myfirstplugin.Myfirstplugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DelwarpCommand implements TabExecutor {

    FileConfiguration config;
    Myfirstplugin plugin;

    public DelwarpCommand (FileConfiguration warpConfig, Myfirstplugin plugin) {
        this.config = warpConfig;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("myfirstplugin.delwarp")) {
                if (args.length == 1) {

                    String warpName = args[0];
                    String warpPath = "warp." + warpName;

                    if (!config.contains(warpPath)) {
                        player.sendMessage("§cWarp bestaat niet");
                        return true;
                    }
                    else {
                        config.set(warpPath, null);
                        plugin.saveConfig(config, "warps.yml");
                        player.sendMessage("Warp " + warpName + " verwijderd");
                        return true;
                    }
                }
                else {
                    player.sendMessage("Kies een warp om te verwijderen");
                    return true;
                }
            }
        }
        else {
            return false;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            ConfigurationSection warps = config.getConfigurationSection("warp");

            if (warps != null) {
                List<String> warpNames = new ArrayList<>(warps.getKeys(false));
                StringUtil.copyPartialMatches(args[0], warpNames, suggestions);
            }
        }

        return suggestions;
    }
}
