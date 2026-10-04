package xyz.inf32768.uspreview;

public class MixinMessenger {
    /**
     * 由于密度函数 {@link net.minecraft.world.level.levelgen.densityfunction.generator.ShiftNoiseFunction.ShiftB} 会反转体积的 X/Z，因此在反转采样时设置这个变量为 true，这样重定位算法就可自动调整
     */
    public static ThreadLocal<Boolean> isSwappedVolume = ThreadLocal.withInitial(() -> false);
}
