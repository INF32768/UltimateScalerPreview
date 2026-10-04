package xyz.inf32768.uspreview.config;

import xyz.inf32768.uspreview.UltimateScalerPreview;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.jspecify.annotations.NonNull;

/**
 * 配置文件热重载器，用于监听游戏内数据重载（包括进入世界或执行 /reload 命令），并重新加载配置文件。
 *
 * @see Config
 */
public class ConfigReloader implements ResourceManagerReloadListener {
    private static final Identifier LISTENER_ID = Identifier.fromNamespaceAndPath(UltimateScalerPreview.MOD_ID, "config_reloader");

    @Override
    public void onResourceManagerReload(@NonNull ResourceManager resourceManager) {
        ConfigManager.loadConfig();
    }

    public static void register() {
        ResourceLoader.get(PackType.SERVER_DATA).registerReloadListener(LISTENER_ID, new ConfigReloader());
    }
}
