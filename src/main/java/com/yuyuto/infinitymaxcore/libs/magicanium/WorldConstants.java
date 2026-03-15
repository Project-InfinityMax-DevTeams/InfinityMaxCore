package com.yuyuto.infinitymaxcore.libs.magicanium;

/**
 * 世界定数（Magicaniumシステム用）
 * <p>
 * - 魔力流量、自然崩壊、干渉などの定数
 * - 調整可能（チューニング用）
 */
public final class WorldConstants {

    private WorldConstants() {}

    /** 不安定係数 λ（安定度計算に使用） */
    public static final double LAMBDA = 0.1;

    /** 干渉減衰長（距離依存の干渉で使用） */
    public static final double YOTA = 5.0;

    /** 拡散係数 α（密度流量計算） */
    public static final double ALPHA = 0.05;

    /** 減衰定数 γ（魔術効力の負密度減衰） */
    public static final double GAMMA = 0.02;

    /** 正密度自然崩壊係数 β */
    public static final double BETA = 0.03;

    /** 負密度自然減衰係数 μ */
    public static final double MU = 0.01;

    /** 空間拡散係数 δ */
    public static final double DELTA = 0.02;

    /** 自己増殖係数 ε（正密度暴走項） */
    public static final double EPSILON = 0.0005;

    /** 過飽和崩壊係数 ζ（正密度立方項） */
    public static final double ZETA = 0.00001;

    /** 干渉係数 η（距離依存干渉で使用） */
    public static final double ETA = 0.1;
}