package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.material.MaterialSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MaterialSystem.class)
public abstract class MixinMaterialSystem {
    @ModifyArgs(method = "getSurfaceDepth", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private static void repositionSurfaceDepth(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }

    @ModifyArgs(method = "getSurfaceSecondary", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private static void repositionSurfaceSecondary(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }

    @ModifyArgs(method = "erodedBadlandsExtension", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 0))
    private static void repositionBadlandsSurface(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }

    @ModifyArgs(method = "erodedBadlandsExtension", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 1))
    private static void repositionBadlandsPillar(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X) * 0.2);
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z) * 0.2);
    }

    @ModifyArgs(method = "erodedBadlandsExtension", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 2))
    private static void repositionBadlandsRoof(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X) * 0.75);
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z) * 0.75);
    }

    @ModifyArgs(method = "frozenOceanExtension", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 0))
    private static void repositionOceanSurface(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }

    @ModifyArgs(method = "frozenOceanExtension", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 1))
    private static void repositionOceanPillar(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X) * 1.28);
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z) * 1.28);
    }

    @ModifyArgs(method = "frozenOceanExtension", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F", ordinal = 2))
    private static void repositionOceanRoof(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X) * 1.17);
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z) * 1.17);
    }

    @ModifyArgs(method = "getBand", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private static void repositionBand(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }
}
