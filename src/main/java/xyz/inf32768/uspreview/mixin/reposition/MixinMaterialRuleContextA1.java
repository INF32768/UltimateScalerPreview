package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(targets = "net/minecraft/world/level/levelgen/material/MaterialRuleContext$1")
public abstract class MixinMaterialRuleContextA1 {
    @ModifyArgs(method = "getAsDouble", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/Noise;get(DDD)F"))
    private static void reposition(Args args) {
        args.set(0, Util.reposition(args.get(0), Direction.Axis.X));
        args.set(2, Util.reposition(args.get(2), Direction.Axis.Z));
    }
}
