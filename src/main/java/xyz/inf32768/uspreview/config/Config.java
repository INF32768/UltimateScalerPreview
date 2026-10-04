package xyz.inf32768.uspreview.config;

import java.math.BigDecimal;

public class Config {
    public static class CachedDouble {
        public static double shiftX = 0.0;
        public static double shiftY = 0.0;
        public static double shiftZ = 0.0;
        public static double scaleX = 1.0;
        public static double scaleY = 1.0;
        public static double scaleZ = 1.0;
    }

    public BigDecimal shiftX = new BigDecimal("0.0");
    public BigDecimal shiftY = new BigDecimal("0.0");
    public BigDecimal shiftZ = new BigDecimal("0.0");
    public BigDecimal scaleX = new BigDecimal("1.0");
    public BigDecimal scaleY = new BigDecimal("1.0");
    public BigDecimal scaleZ = new BigDecimal("1.0");
    public boolean highPrecision = false;
    public boolean forceFarLands = false;
    public boolean oldWrap = false;
    public boolean noSmooth = false;
    public boolean scattered = false;
    public double xzScale = 684.412;
    public double yScale = 684.412;

    public Config() {
    }
}
