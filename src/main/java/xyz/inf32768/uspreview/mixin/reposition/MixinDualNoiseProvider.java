package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DualNoiseProvider.class)
public abstract class MixinDualNoiseProvider {
    @Shadow
    @Final
    private float slowScale;

    @ModifyArgs(method = "getSlowNoiseValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void reposition(Args args, final BlockPos pos) {
        args.set(0, Util.reposition(pos.getX(), Direction.Axis.X) * this.slowScale);
        args.set(1, Util.reposition(pos.getY(), Direction.Axis.Y) * this.slowScale);
        args.set(2, Util.reposition(pos.getZ(), Direction.Axis.Z) * this.slowScale);
    }
}
