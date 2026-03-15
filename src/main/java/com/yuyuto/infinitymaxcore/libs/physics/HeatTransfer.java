package com.yuyuto.infinitymaxcore.libs.physics;

/**
 * <h2>HeatTransfer（熱伝導）</h2>
 *
 * <p>
 * フーリエの法則に基づく熱伝導。
 * </p>
 *
 * <pre>
 * Q = k A (ΔT / L) Δt
 * </pre>
 *
 * 熱は「あたたかい方」から「つめたい方」へ流れます。
 * 温度差がないと熱は動きません。
 */
public final class HeatTransfer implements PhysicalPhenomenon {

    /** 熱伝導率 [W/(m·K)] */
    private final double thermalConductivity;

    /** 接触面積 [m²] */
    private final double area;

    /** 距離（厚み）[m] */
    private final double distance;

    /** 比熱 [J/(kg·K)] */
    private final double specificHeat;

    public HeatTransfer(double thermalConductivity,double area,double distance,double specificHeat) {

        this.thermalConductivity = thermalConductivity;
        this.area = area;
        this.distance = distance;
        this.specificHeat = specificHeat;
    }

    @Override
    public PhysicalState apply(PhysicalState state, double deltaTime) {

        /*
         * フーリエの法則
         */
        double deltaT = state.temperature().getSI();
        // 本来は隣接セルとの差を使うべき

        double heatFlowRate = thermalConductivity * area * (deltaT / distance);
        double dQ = heatFlowRate * deltaTime;

        /*
         * 温度変化:
         * ΔT = Q / (m c)
         */
        double mass = state.mass().getSI();
        double temperatureChange = dQ / (mass * specificHeat);
        double newTempValue = state.temperature().getSI() - temperatureChange;
        Energy newEnergy = new Energy(state.internalEnergy().getSI() + dQ,Energy.JOULE);
        Temperature newTemp =new Temperature(newTempValue, Temperature.KELVIN);

        return new PhysicalState(newTemp, state.pressure(), state.density(), newEnergy, state.phase(), state.mass(),state.material());
    }
}