package xyz.inf32768.uspreview.mixin.reposition;

import xyz.inf32768.uspreview.MixinMessenger;
import xyz.inf32768.uspreview.Util;
import xyz.inf32768.uspreview.config.ConfigManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.densityfunction.DensityBuffer;
import net.minecraft.world.level.levelgen.densityfunction.DensityVolume;
import net.minecraft.world.level.levelgen.synth.GradientNoise;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PerlinNoise.class)
public abstract class MixinPerlinNoise extends GradientNoise {
    protected MixinPerlinNoise(RandomSource random) {
        super(random);
    }

    @ModifyVariable(method = "addToVolume", at = @At("STORE"), name = "x")
    private double repositionX(double x, final DensityBuffer buffer, final DensityVolume volume, final double xzScale, final double yScale, final float amplitude, @Local(name = "indexX") int indexX) {
        return repositionScatterable(volume, xzScale, indexX, Direction.Axis.X, MixinMessenger.isSwappedVolume.get() ? Direction.Axis.Z : Direction.Axis.X) + this.offsetX;
    }

    @ModifyVariable(method = "addToVolume", at = @At("STORE"), name = "y")
    private double repositionY(double y, final DensityBuffer buffer, final DensityVolume volume, final double xzScale, final double yScale, final float amplitude, @Local(name = "indexY") int indexY) {
        return repositionScatterable(volume, yScale, indexY, Direction.Axis.Y, MixinMessenger.isSwappedVolume.get() ? Direction.Axis.X : Direction.Axis.Y) + this.offsetY;
    }

    @ModifyVariable(method = "addToVolume", at = @At("STORE"), name = "z")
    private double repositionZ(double z, final DensityBuffer buffer, final DensityVolume volume, final double xzScale, final double yScale, final float amplitude, @Local(name = "indexZ") int indexZ) {
        return repositionScatterable(volume, xzScale, indexZ, Direction.Axis.Z, Direction.Axis.Z) + this.offsetZ;
    }

    @Unique
    private static double repositionScatterable(DensityVolume volume, double scale, int index, Direction.Axis blockAxis, Direction.Axis repositionAxis) {
        int minBlock = switch (blockAxis) {
            case X -> volume.minBlockX();
            case Y -> volume.minBlockY();
            case Z -> volume.minBlockZ();
        };

        int stepBlock = switch (blockAxis) {
            case X -> volume.stepBlockX();
            case Y -> volume.stepBlockY();
            case Z -> volume.stepBlockZ();
        };

        double repositionScale = switch (repositionAxis) {
            case X -> ConfigManager.config.scaleX.doubleValue();
            case Y -> ConfigManager.config.scaleY.doubleValue();
            case Z -> ConfigManager.config.scaleZ.doubleValue();
        };

        return ConfigManager.config.scattered
                ? wrap(Util.reposition(minBlock, repositionAxis) * scale) + index * scale * stepBlock * repositionScale
                : wrap(Util.reposition(minBlock + index * stepBlock, repositionAxis) * scale);
    }
}
