package games.toost.myfirstplugin.commands.warps;

import games.toost.myfirstplugin.Myfirstplugin;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.kyori.adventure.text.Component;

import static net.kyori.adventure.text.event.HoverEvent.hoverEvent;
import static net.kyori.adventure.text.event.HoverEvent.showText;

public class WarpsListCommand implements CommandExecutor {

    FileConfiguration config;
    Myfirstplugin plugin;

    public WarpsListCommand(FileConfiguration warpConfig, Myfirstplugin plugin) {
        this.config = warpConfig;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {

            ConfigurationSection warps = config.getConfigurationSection("warp");

            if (warps == null) {
                player.sendMessage("§cEr zijn nog geen warps");
                return true;
            }

            List<String> warpNames = new ArrayList<>(warps.getKeys(false));
            Collections.sort(warpNames);

            Component message = Component.text("Beschikbare warps: ");

            for (int i = 0; i < warpNames.size(); i++) {
                String warpName = warpNames.get(i);
                message = message.append(Component.text(warpName)
                            .clickEvent(ClickEvent.runCommand("/warp " + warpName))
                            .hoverEvent(HoverEvent.showText(Component.text("Warp naar: " + warpName)))
                );

                if (i < warpNames.size() - 1) {
                    message = message.append(Component.text(", "));
                }
            }
            player.sendMessage(message);
            return true;
        }

        return false;
    }
}
