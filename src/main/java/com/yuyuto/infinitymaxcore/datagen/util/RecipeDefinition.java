package com.yuyuto.infinitymaxcore.datagen.util;

import net.minecraft.data.recipes.RecipeCategory;

import java.util.List;
import java.util.Map;

public abstract class RecipeDefinition {

    public static class Shaped extends RecipeDefinition {

        private final List<String> pattern;
        private final Map<String, String> keys;

        public Shaped(List<String> pattern, Map<String, String> keys) {
            this.pattern = pattern;
            this.keys = keys;
        }

        public List<String> getPattern() {
            return pattern;
        }

        public Map<String, String> getKeys() {
            return keys;
        }
    }

    public static class Shapeless extends RecipeDefinition {

        private final List<String> ingredients;

        public Shapeless(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public List<String> getIngredients() {
            return ingredients;
        }
    }

    public static class Blasting extends Cooking{
        public Blasting(String ingredient, RecipeCategory category, float exp, int time){
            super(ingredient, category, exp, time);
        }
    }

    public static class Smelting extends Cooking{

        public Smelting(String ingredient, RecipeCategory category, float exp, int time){
            super(ingredient, category, exp, time);
        }
    }

    public static class Smoking extends Cooking{
        public Smoking(String ingredient, RecipeCategory category, float exp, int time){
            super(ingredient, category, exp, time);
        }
    }

    public static class Stonecutting extends RecipeDefinition{
        private final String ingredient;
        private final int resultCount;
        private final RecipeCategory category;

        public Stonecutting(String ingredient, RecipeCategory category, int resultCount){
            this.ingredient = ingredient;
            this.resultCount = resultCount;
            this.category = category;
        }

        public String getIngredient() {
            return ingredient;
        }

        public int getResultCount() {
            return resultCount;
        }

        public RecipeCategory getCategory() {
            return category;
        }
    }

    public static class Smithing extends RecipeDefinition{

        private final String template;
        private final RecipeCategory category;
        private final String base;
        private final String addition;
        private final String result;


        public Smithing(String template, RecipeCategory category, String base, String addition, String result){
            this.template = template;
            this.category = category;
            this.base = base;
            this.addition = addition;
            this.result = result;
        }

        public String getTemplate() {
            return template;
        }

        public String getBase() {
            return base;
        }

        public String getAddition() {
            return addition;
        }

        public String getResult() {
            return result;
        }

        public RecipeCategory getCategory() {
            return category;
        }
    }
}