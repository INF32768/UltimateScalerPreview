package xyz.inf32768.uspreview;

import xyz.inf32768.uspreview.config.Config;
import xyz.inf32768.uspreview.config.ConfigManager;
import net.minecraft.core.Direction;

import java.math.BigDecimal;

public class Util {
    private Util() {}

    public static double reposition(Number original, Direction.Axis axis) {
        if (ConfigManager.config.highPrecision) {
            BigDecimal shift = switch (axis) {
                case X -> ConfigManager.config.shiftX;
                case Y -> ConfigManager.config.shiftY;
                case Z -> ConfigManager.config.shiftZ;
            };

            BigDecimal scale = switch (axis) {
                case X -> ConfigManager.config.scaleX;
                case Y -> ConfigManager.config.scaleY;
                case Z -> ConfigManager.config.scaleZ;
            };
            return BigDecimal.valueOf(original.doubleValue()).multiply(scale).add(shift).doubleValue();
        }

        double shift = switch (axis) {
            case X -> Config.CachedDouble.shiftX;
            case Y -> Config.CachedDouble.shiftY;
            case Z -> Config.CachedDouble.shiftZ;
        };

        double scale = switch (axis) {
            case X -> Config.CachedDouble.scaleX;
            case Y -> Config.CachedDouble.scaleY;
            case Z -> Config.CachedDouble.scaleZ;
        };
        return original.doubleValue() * scale + shift;
    }

    public static BigDecimal repositionBig(Number original, Direction.Axis axis) {
        if (!ConfigManager.config.highPrecision) throw new IllegalStateException("Cannot reposition big numbers when high precision is disabled");
        BigDecimal shift = switch (axis) {
            case X -> ConfigManager.config.shiftX;
            case Y -> ConfigManager.config.shiftY;
            case Z -> ConfigManager.config.shiftZ;
        };

        BigDecimal scale = switch (axis) {
            case X -> ConfigManager.config.scaleX;
            case Y -> ConfigManager.config.scaleY;
            case Z -> ConfigManager.config.scaleZ;
        };
        return BigDecimal.valueOf(original.doubleValue()).multiply(scale).add(shift);
    }
}
