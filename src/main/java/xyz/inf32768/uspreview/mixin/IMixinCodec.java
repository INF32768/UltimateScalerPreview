package xyz.inf32768.uspreview.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Codec.class)
public interface IMixinCodec {
    @Inject(method = "checkRange", at = @At("HEAD"), cancellable = true)
    private static <N extends Number & Comparable<N>> void dontCheck(CallbackInfoReturnable<Function<N, DataResult<N>>> cir) {
        cir.setReturnValue(DataResult::success);
    }
}
