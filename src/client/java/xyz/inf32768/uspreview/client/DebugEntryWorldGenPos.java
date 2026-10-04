package xyz.inf32768.uspreview.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import xyz.inf32768.uspreview.Util;
import xyz.inf32768.uspreview.config.ConfigManager;

public class DebugEntryWorldGenPos implements DebugScreenEntry {
    private static final Identifier GROUP = Identifier.withDefaultNamespace("world_gen_pos");
    private @Nullable BlockPos lastPos = null;
    private String result;

    @Override
    public void display(
            final @NonNull DebugScreenDisplayer displayer,
            final @Nullable Level serverOrClientLevel,
            final @Nullable LevelChunk clientChunk,
            final @Nullable LevelChunk serverChunk
    ) {
        Minecraft minecraft = Minecraft.getInstance();
        Entity entity = minecraft.getCameraEntity();
        ServerLevel serverLevel = serverOrClientLevel instanceof ServerLevel level ? level : null;
        if (entity != null && serverLevel != null) {
            BlockPos feetPos = entity.blockPosition();
            if (!feetPos.equals(this.lastPos)) {
                this.update(feetPos);
            }

            displayer.addToGroup(GROUP, this.result);
        }
    }

    private void update(BlockPos feetPos) {
        this.lastPos = feetPos;
        this.result = "WorldGenXYZ: %s %s %s".formatted(Util.reposition(feetPos.getX(), Direction.Axis.X), Util.reposition(feetPos.getY(), Direction.Axis.Y), Util.reposition(feetPos.getZ(), Direction.Axis.Z));
        if (ConfigManager.config.highPrecision) this.result += "\nWorldGenXYZ(big): %s %s %s".formatted(Util.repositionBig(feetPos.getX(), Direction.Axis.X), Util.repositionBig(feetPos.getY(), Direction.Axis.Y), Util.repositionBig(feetPos.getZ(), Direction.Axis.Z));
    }
}
