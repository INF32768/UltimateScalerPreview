package xyz.inf32768.uspreview.mixin;

import xyz.inf32768.uspreview.config.ConfigManager;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.synth.GradientNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GradientNoise.class)
public abstract class MixinGradientNoise {
    @Inject(method = "wrap", at = @At("HEAD"), cancellable = true)
    private static void dontWrap(double x, CallbackInfoReturnable<Double> cir) {
        if (ConfigManager.config.forceFarLands) cir.setReturnValue(x);
    }

    @Inject(method = "wrap", at = @At("RETURN"), cancellable = true)
    private static void oldWrap(double x, CallbackInfoReturnable<Double> cir) {
        if (ConfigManager.config.forceFarLands) cir.setReturnValue(x - Mth.lfloor(x / 3.3554432E7 + 0.5) * 3.3554432E7);
    }
}
