package com.yuyuto.infinitymaxcore.datagen.util;

import com.yuyuto.infinitymaxcore.recipe.RecipeDefinition;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;

public abstract class Cooking extends RecipeDefinition {

    private final String ingredient;
    private final RecipeCategory category;
    private final float experience;
    private final int cookingTime;

    public Cooking(ResourceLocation result, String ingredient, RecipeCategory category, float experience, int cookingTime){
        super(result);
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