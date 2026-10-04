package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseBasedStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(NoiseBasedStateProvider.class)
public abstract class MixinNoiseBasedStateProvider {
    @ModifyArgs(method = "getNoiseValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void reposition(Args args, final BlockPos pos, final double scale) {
        args.set(0, Util.reposition(pos.getX(), Direction.Axis.X) * scale);
        args.set(1, Util.reposition(pos.getY(), Direction.Axis.Y) * scale);
        args.set(2, Util.reposition(pos.getZ(), Direction.Axis.Z) * scale);
    }
}
