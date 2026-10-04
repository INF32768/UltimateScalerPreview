package xyz.inf32768.uspreview.mixin;

import xyz.inf32768.uspreview.config.ConfigManager;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mth.class)
public abstract class MixinMth {
    @Inject(method = "smoothstep", at = @At("HEAD"), cancellable = true)
    private static void noSmooth(float x, CallbackInfoReturnable<Float> cir) {
        if (ConfigManager.config.noSmooth) cir.setReturnValue(x);
    }

    @Inject(method = "smoothstepDerivative", at = @At("HEAD"), cancellable = true)
    private static void noSmoothDerivative(float x, CallbackInfoReturnable<Float> cir) {
        if (ConfigManager.config.noSmooth) cir.setReturnValue(1F);
    }
}
