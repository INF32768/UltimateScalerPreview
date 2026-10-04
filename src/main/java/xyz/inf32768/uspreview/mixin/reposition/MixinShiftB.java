package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.MixinMessenger;
import xyz.inf32768.uspreview.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.densityfunction.SamplerContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(targets = "net/minecraft/world/level/levelgen/densityfunction/generator/ShiftNoiseFunction$ShiftB$1")
public abstract class MixinShiftB {
    @ModifyArgs(method = "sampleValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void repositionPoint(Args args, SamplerContext context, int blockX, int blockY, int blockZ) {
        args.set(0, Util.reposition(blockZ, Direction.Axis.Z) * 0.25);
        args.set(1, Util.reposition(blockX, Direction.Axis.X) * 0.25);
    }

    @Inject(method = "sampleVolume", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;addToVolume(Lnet/minecraft/world/level/levelgen/densityfunction/DensityBuffer;Lnet/minecraft/world/level/levelgen/densityfunction/DensityVolume;DDF)V"))
    private void addRepositionFlag(CallbackInfo ci) {
        MixinMessenger.isSwappedVolume.set(true);
    }

    @Inject(method = "sampleVolume", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/densityfunction/DensityVolume;sizeZ()I"))
    private void removeRepositionFlag(CallbackInfo ci) {
        MixinMessenger.isSwappedVolume.set(false);
    }
}
