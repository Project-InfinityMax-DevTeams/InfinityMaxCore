package com.yuyuto.infinitymaxcore.item;

public class FoodDefinition {

    //Food関連の情報を集積する

    private int nutrition;
    private float saturation;
    private boolean alwaysEat;
    private boolean meat;
    private boolean fastFood;

    public int getNutrition() {
        return nutrition;
    }

    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public boolean isAlwaysEat() {
        return alwaysEat;
    }

    public void setAlwaysEat(boolean alwaysEat) {
        this.alwaysEat = alwaysEat;
    }

    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public boolean isFastFood() {
        return fastFood;
    }

    public void setFastFood(boolean fastFood) {
        this.fastFood = fastFood;
    }
}
