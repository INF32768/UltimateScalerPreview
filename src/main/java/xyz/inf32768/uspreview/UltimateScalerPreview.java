package xyz.inf32768.uspreview;

import xyz.inf32768.uspreview.config.ConfigManager;
import xyz.inf32768.uspreview.config.ConfigReloader;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class UltimateScalerPreview implements ModInitializer {
    public static final String MOD_ID = "us-preview";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), MOD_ID + ".toml");

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        if (CONFIG_FILE.exists()) ConfigManager.loadConfig();
        else ConfigManager.saveConfig();
        ConfigReloader.register();
    }
}
