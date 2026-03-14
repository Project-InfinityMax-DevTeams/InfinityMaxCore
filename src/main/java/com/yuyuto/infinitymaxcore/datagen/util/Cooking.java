package com.yuyuto.infinitymaxcore.datagen.util;

public abstract class Cooking extends RecipeDefinition{

    private final String ingredient;
    private final float experience;
    private final int cookingTime;

    public Cooking(String ingredient, float experience, int cookingTime){
        this.ingredient = ingredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public String getIngredient() {
        return ingredient;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}