package com.yuyuto.infinitymaxcore.libs.magicanium;

/**
 * 3次元ベクトルクラス
 * <p>
 * 魔術干渉や空間座標計算に利用。
 */
public record Vector3d(double x, double y, double z) {

    /**
     * 他のベクトルまでの距離を計算（ユークリッド距離）
     *
     * @param other 目標ベクトル
     * @return 距離
     */
    public double distanceTo(Vector3d other) {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}