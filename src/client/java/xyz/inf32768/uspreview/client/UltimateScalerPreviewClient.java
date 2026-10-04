package xyz.inf32768.uspreview.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.resources.Identifier;
import xyz.inf32768.uspreview.UltimateScalerPreview;

public class UltimateScalerPreviewClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        DebugScreenEntries.register(Identifier.fromNamespaceAndPath(UltimateScalerPreview.MOD_ID, "world_gen_pos"), new DebugEntryWorldGenPos());
    }
}