package xyz.inf32768.uspreview.config;

import xyz.inf32768.uspreview.UltimateScalerPreview;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

import java.io.IOException;

public class ConfigManager {
    public static Config config = new Config();

    public static void loadConfig() {
        Toml toml = new Toml().read(UltimateScalerPreview.CONFIG_FILE);
        config = toml.to(Config.class);
        Config.CachedDouble.shiftX = config.shiftX.doubleValue();
        Config.CachedDouble.shiftY = config.shiftY.doubleValue();
        Config.CachedDouble.shiftZ = config.shiftZ.doubleValue();
        Config.CachedDouble.scaleX = config.scaleX.doubleValue();
        Config.CachedDouble.scaleY = config.scaleY.doubleValue();
        Config.CachedDouble.scaleZ = config.scaleZ.doubleValue();
    }

    public static void saveConfig() {
        TomlWriter tomlWriter = new TomlWriter();
        try {
            tomlWriter.write(config, UltimateScalerPreview.CONFIG_FILE);
        } catch (IOException e) {
            UltimateScalerPreview.LOGGER.error("Failed to save config file: ", e);
        }
    }
}
