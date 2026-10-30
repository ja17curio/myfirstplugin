package games.toost.myfirstplugin.helpers;

import games.toost.myfirstplugin.Myfirstplugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.StringUtil;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConfigHelper {

    public static List<String> getConfigKeys(String section, String search, FileConfiguration config) {
        List<String> suggestions = new ArrayList<>();

        ConfigurationSection configList = config.getConfigurationSection(section);

        if (configList != null) {
            List<String> configKeys = new ArrayList<>(configList.getKeys(false));
            StringUtil.copyPartialMatches(search, configKeys, suggestions);
        }

        return suggestions;
    }

    public static void saveConfig(FileConfiguration config, Myfirstplugin plugin, String configName, @Nullable Object saveData, String saveKey) {

        config.set(saveKey, saveData);
        plugin.saveConfig(config, configName);

    }
}
