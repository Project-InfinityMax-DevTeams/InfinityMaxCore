package com.yuyuto.infinitymaxcore.libs.physics;

/**
 * ===============================================
 * ReflectiveBoundary
 * ===============================================
 * <p>
 * 【概要】
 * 壁の境界条件を表します。
 * <p>
 * 物理的には「反射壁（鏡のような壁）」を意味します。
 * <p>
 * 例：
 * ・密閉容器の壁
 * ・理想的な剛体壁
 * <p>
 * 【物理的意味】
 * 壁を通って物質やエネルギーは外へ出ません。
 * つまり「流れを遮断」します。
 * <p>
 * 本来の流体力学では：
 * ・速度の法線方向成分を反転させる
 * ・質量・エネルギーは保存
 * <p>
 * しかしこの簡易実装では
 * 「何も変更しない」＝外へ出ない
 * という簡略モデルになっています。
 */
public final class ReflectiveBoundary implements BoundaryCondition {

    @Override
    public PhysicalState apply(PhysicalState internal,
                               PhysicalState external) {

        /*
         * internal  = セル内部の物理状態
         * external  = セル外側の物理状態（通常は未使用）
         *
         * 本来の反射壁では：
         * 速度ベクトルの壁に垂直な成分を反転させます。
         *
         * しかしこの実装では
         * 「内部状態をそのまま返す」ことで
         * 外部との相互作用を完全に遮断しています。
         *
         * つまり：
         * 壁を通して何も流れない
         */

        return internal;
    }
}