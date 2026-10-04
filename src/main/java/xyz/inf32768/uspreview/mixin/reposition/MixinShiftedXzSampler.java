package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.densityfunction.*;
import net.minecraft.world.level.levelgen.densityfunction.generator.NoiseFunction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(NoiseFunction.ShiftedXzSampler.class)
public abstract class MixinShiftedXzSampler {
    @Shadow
    @Final
    private double xzScale;

    @Shadow
    @Final
    private double yScale;

    @Shadow
    @Final
    private DensitySampler shiftX;

    @Shadow
    @Final
    private DensitySampler shiftZ;

    @ModifyArgs(method = "sampleValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void repositionSampler(Args args, final SamplerContext context, final int blockX, final int blockY, final int blockZ) {
        args.set(0, Util.reposition(blockX, Direction.Axis.X) * this.xzScale + this.shiftX.sampleValue(context, blockX, blockY, blockZ));
        args.set(1, Util.reposition(blockY, Direction.Axis.Y) * this.yScale);
        args.set(2, Util.reposition(blockZ, Direction.Axis.Z) * this.xzScale + this.shiftZ.sampleValue(context, blockX, blockY, blockZ));
    }

    @ModifyArgs(method = "sampleVolume", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void repositionSamplerVolume(Args args, final SamplerContext context, final DensityBuffer outputBuffer, final DensityVolume volume,
                                         @Local(name = "shiftZBuffer") ScopedDensityBuffer shiftZBuffer,
                                         @Local(name = "x") int x, @Local(name = "y") int y, @Local(name = "z") int z, @Local(name = "index") int index) {
        args.set(0, Util.reposition(volume.blockX(x), Direction.Axis.X) * this.xzScale + outputBuffer.get(index));
        args.set(1, Util.reposition(volume.blockY(y), Direction.Axis.Y) * this.yScale);
        args.set(2, Util.reposition(volume.blockZ(z), Direction.Axis.Z) * this.xzScale + shiftZBuffer.get(index));
    }
}
