package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.MixinMessenger;
import xyz.inf32768.uspreview.Util;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.densityfunction.DensityBuffer;
import net.minecraft.world.level.levelgen.densityfunction.DensityVolume;
import net.minecraft.world.level.levelgen.synth.Noise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Noise.class)
public interface IMixinNoise {
    @ModifyArgs(method = "addToVolume", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private static void repositionVolume(Args args, final DensityBuffer buffer, final DensityVolume volume, final double xzScale, final double yScale, final float amplitude,
                                         @Local(name = "indexX") int indexX, @Local(name = "indexY") int indexY, @Local(name = "indexZ") int indexZ) {
        double x = Util.reposition(volume.blockX(indexX), Direction.Axis.X) * xzScale;
        double y = Util.reposition(volume.blockY(indexY), Direction.Axis.Y) * yScale;
        double z = Util.reposition(volume.blockZ(indexZ), Direction.Axis.Z) * xzScale;

        args.set(0, MixinMessenger.isSwappedVolume.get() ? z : x);
        args.set(1, y);
        args.set(2, MixinMessenger.isSwappedVolume.get() ? x : z);
    }
}
