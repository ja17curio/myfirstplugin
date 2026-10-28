package games.toost.myfirstplugin;

import games.toost.myfirstplugin.commands.communication.BroadcastCommand;
import games.toost.myfirstplugin.commands.misc.HealCommand;
import games.toost.myfirstplugin.commands.teleports.SpawnCommand;
import games.toost.myfirstplugin.commands.teleports.TeleportCommand;
import games.toost.myfirstplugin.commands.warps.SetwarpCommand;
import games.toost.myfirstplugin.commands.warps.WarpCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public final class Myfirstplugin extends JavaPlugin {


    private FileConfiguration warpConfig;
    @Override
    public void onEnable() {
        //load warps config
        warpConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder() + "/warps.yml"));

        // Plugin startup logic
        //communication commands
        getCommand("broadcast").setExecutor(new BroadcastCommand());

        //teleport commands
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        //misc commands
        getCommand("heal").setExecutor(new HealCommand());

        //warp commands
        WarpCommand warpCommand = new WarpCommand(warpConfig, this);
        getCommand("setwarp").setExecutor(warpCommand);
        getCommand("warp").setExecutor(warpCommand);
        getCommand("warp").setTabCompleter(warpCommand);


        // TODO WARP LIST COMMAND
        // TODO WARP DELETE COMMAND
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void saveConfig(FileConfiguration config, String name) {
        File configFile = new File(getDataFolder() + "/" + name);
        try{
            if (!configFile.exists()) {
                getDataFolder().mkdirs();
                configFile.createNewFile();
            }
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
