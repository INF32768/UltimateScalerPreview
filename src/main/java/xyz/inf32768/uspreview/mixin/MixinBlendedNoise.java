package xyz.inf32768.uspreview.mixin;

import xyz.inf32768.uspreview.config.ConfigManager;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlendedNoise.class)
public abstract class MixinBlendedNoise {
//    @Shadow
//    @Final
//    double val$xzMultiplier;
//
//    @Shadow
//    @Final
//    private double val$yMultiplier;
//
//    @ModifyVariable(method = "compute", at = @At("STORE"), name = "limitX")
//    private double modifyLimitX(double limitX, DensityFunction.FunctionContext context) {
//        return new BigDecimal(context.blockX()).add(UltimateScalerPreview.shiftX).multiply(UltimateScalerPreview.scaleX).doubleValue() * this.val$xzMultiplier;
//    }
//
//    @ModifyVariable(method = "compute", at = @At("STORE"), name = "limitY")
//    private double modifyLimitY(double limitY, DensityFunction.FunctionContext context) {
//        return new BigDecimal(context.blockY()).add(UltimateScalerPreview.shiftY).multiply(UltimateScalerPreview.scaleY).doubleValue() * this.val$yMultiplier;
//    }
//
//    @ModifyVariable(method = "compute", at = @At("STORE"), name = "limitZ")
//    private double modifyLimitZ(double limitZ, DensityFunction.FunctionContext context) {
//        return new BigDecimal(context.blockZ()).add(UltimateScalerPreview.shiftZ).multiply(UltimateScalerPreview.scaleZ).doubleValue() * this.val$xzMultiplier;
//    }

    @Shadow
    @Final
    private double xzScale;

    @Shadow
    @Final
    private double yScale;

    @Inject(method = "xzMultiplier", at = @At("HEAD"), cancellable = true)
    private void modifyXzMultiplier(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(ConfigManager.config.xzScale * this.xzScale);
    }

    @Inject(method = "yMultiplier", at = @At("HEAD"), cancellable = true)
    private void modifyYMultiplier(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(ConfigManager.config.yScale * this.yScale);
    }
}
