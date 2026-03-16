package com.yuyuto.infinitymaxcore.recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    private static final List<RecipeDefinition> RECIPES = new ArrayList<>();

    public static void register(RecipeDefinition recipe){
        RECIPES.add(recipe);
    }

    public static List<RecipeDefinition> getAll(){
        return RECIPES;
    }
}
