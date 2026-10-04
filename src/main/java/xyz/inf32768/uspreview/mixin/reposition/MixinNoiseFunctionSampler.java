package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.densityfunction.SamplerContext;
import net.minecraft.world.level.levelgen.densityfunction.generator.NoiseFunction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(NoiseFunction.Sampler.class)
public abstract class MixinNoiseFunctionSampler {
    @Shadow
    @Final
    private double xzScale;

    @Shadow
    @Final
    private double yScale;

    @ModifyArgs(method = "sampleValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void repositionSampler(Args args, final SamplerContext context, final int blockX, final int blockY, final int blockZ) {
        args.set(0, Util.reposition(blockX, Direction.Axis.X) * this.xzScale);
        args.set(1, Util.reposition(blockY, Direction.Axis.Y) * this.yScale);
        args.set(2, Util.reposition(blockZ, Direction.Axis.Z) * this.xzScale);
    }
}
