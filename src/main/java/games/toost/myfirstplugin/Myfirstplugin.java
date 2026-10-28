package games.toost.myfirstplugin;

import games.toost.myfirstplugin.commands.communication.BroadcastCommand;
import games.toost.myfirstplugin.commands.misc.HealCommand;
import games.toost.myfirstplugin.commands.teleports.SpawnCommand;
import games.toost.myfirstplugin.commands.teleports.TeleportCommand;
import games.toost.myfirstplugin.commands.warps.SetwarpCommand;
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
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setwarp").setExecutor(new SetwarpCommand(warpConfig, this));
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
