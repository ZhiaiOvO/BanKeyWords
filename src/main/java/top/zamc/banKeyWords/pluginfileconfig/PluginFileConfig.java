package top.zamc.banKeyWords.pluginfileconfig;

import com.sun.jdi.Value;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.K;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PluginFileConfig extends YamlConfiguration {
    private Map<String, Object> newDefaultMap;
    @NotNull
    @Override
    public String saveToString() {
        return null;
    }

    public PluginFileConfig() {
    }

    public PluginFileConfig(@Nullable Configuration defaults) {
        super();
    }

    public PluginFileConfig(@Nullable Map<String, Object> newDefaultMap) {
        Configuration newDefaults = new PluginFileConfig();
        newDefaults.set("words", newDefaultMap);
        new PluginFileConfig(newDefaults);
    }

    @Override
    public void addDefault(@NotNull String path, @Nullable Object value) {
        super.addDefault(path, value);
    }
    public void add(@NotNull String path, @Nullable Object value){
        Configuration defaults = getDefaults();
        Configuration newDefaults = new PluginFileConfig((Map<String, Object>) defaults.getValues(true).put(path, value));

        if (defaults != null) {
            newDefaults.set("words", defaults.getValues(true));
        }

    }
    @Override
    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {

    }
}