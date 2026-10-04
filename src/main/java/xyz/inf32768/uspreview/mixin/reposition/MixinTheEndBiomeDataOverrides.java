package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.Util;
import net.fabricmc.fabric.impl.biome.TheEndBiomeData;
import net.fabricmc.fabric.impl.biome.WeightedPicker;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;

@Mixin(TheEndBiomeData.Overrides.class)
public abstract class MixinTheEndBiomeDataOverrides {
    @ModifyArgs(method = "pick(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Ljava/util/Map;IILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/impl/biome/WeightedPicker;pickFromNoise(Lnet/minecraft/world/level/levelgen/synth/PerlinNoise;DDD)Ljava/lang/Object;"))
    private <T extends Holder<Biome>> void repositionSampler(Args args, T key, T defaultValue, Map<T, WeightedPicker<T>> pickers, int x, int z, Climate.Sampler noise) {
        // 这里的 x/z 是 QuartPos，重定位时需先乘以 4 来还原
        args.set(1, Util.reposition(x << 2, Direction.Axis.X) / 256.0);
        args.set(3, Util.reposition(z << 2, Direction.Axis.Z) / 256.0);
    }
}
