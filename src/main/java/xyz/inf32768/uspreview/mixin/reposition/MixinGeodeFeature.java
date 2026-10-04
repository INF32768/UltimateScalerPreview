package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.GeodeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GeodeFeature.class)
public abstract class MixinGeodeFeature {
    @ModifyArgs(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private static void repositionFilterShiftX(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(1, Util.reposition(args.get(1), Direction.Axis.Y));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }
}
