package com.yuyuto.infinitymaxcore.libs.magicanium;

/**
 * SpellStructure
 * <p>
 * 術式構造
 * - κ : 構造係数（術式の完成度や強さ）
 * - info : 術式情報ベクトル（多次元情報）
 */
public record SpellStructure(double kappa, InformationVector info) {

}