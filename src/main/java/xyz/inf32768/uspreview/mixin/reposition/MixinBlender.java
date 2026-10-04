package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Blender.class)
public abstract class MixinBlender {
    @ModifyArgs(method = "lambda$createAroundOldChunksCarvingMaskFilter$0(Lnet/minecraft/world/level/levelgen/blending/Blender$DistanceGetter;III)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 0))
    private static void repositionFilterShiftX(Args args, @Local(name = "x", argsOnly = true) int x, @Local(name = "y", argsOnly = true) int y, @Local(name = "z", argsOnly = true) int z) {
        args.set(0, Util.reposition(x, Direction.Axis.X));
        args.set(1, Util.reposition(y, Direction.Axis.Y));
        args.set(2, Util.reposition(z, Direction.Axis.Z));
    }

    @ModifyArgs(method = "lambda$createAroundOldChunksCarvingMaskFilter$0(Lnet/minecraft/world/level/levelgen/blending/Blender$DistanceGetter;III)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 1))
    private static void repositionFilterShiftY(Args args, @Local(name = "x", argsOnly = true) int x, @Local(name = "y", argsOnly = true) int y, @Local(name = "z", argsOnly = true) int z) {
        args.set(0, Util.reposition(y, Direction.Axis.Y));
        args.set(1, Util.reposition(z, Direction.Axis.Z));
        args.set(2, Util.reposition(x, Direction.Axis.X));
    }

    @ModifyArgs(method = "lambda$createAroundOldChunksCarvingMaskFilter$0(Lnet/minecraft/world/level/levelgen/blending/Blender$DistanceGetter;III)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 2))
    private static void repositionFilterShiftZ(Args args, @Local(name = "x", argsOnly = true) int x, @Local(name = "y", argsOnly = true) int y, @Local(name = "z", argsOnly = true) int z) {
        args.set(0, Util.reposition(z, Direction.Axis.Z));
        args.set(1, Util.reposition(x, Direction.Axis.X));
        args.set(2, Util.reposition(y, Direction.Axis.Y));
    }

    @ModifyArgs(method = "blendBiome", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private void repositionSampler(Args args, final int quartX, final int quartY, final int quartZ) {
        args.set(0, Util.reposition(quartX << 2, Direction.Axis.X) / 4.0);
        args.set(2, Util.reposition(quartZ << 2, Direction.Axis.Z) / 4.0);
    }
}
