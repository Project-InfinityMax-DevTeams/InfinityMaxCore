package com.yuyuto.infinitymaxcore.datagen.util;

import net.minecraft.data.recipes.RecipeCategory;

public abstract class Cooking extends RecipeDefinition{

    private final String ingredient;
    private final RecipeCategory category;
    private final float experience;
    private final int cookingTime;

    public Cooking(String ingredient, RecipeCategory category, float experience, int cookingTime){
        this.ingredient = ingredient;
        this.category = category;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public String getIngredient() {
        return ingredient;
    }

    public RecipeCategory getRecipeCategory(){
        return category;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}