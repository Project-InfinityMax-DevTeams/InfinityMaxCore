package com.yuyuto.infinitymaxcore.recipe;

import com.yuyuto.infinitymaxcore.datagen.util.Cooking;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public abstract class RecipeDefinition {

    private final String result;

    protected RecipeDefinition(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }


    public static class Shaped extends RecipeDefinition {

        private final List<String> pattern;
        private final Map<String, String> keys;

        public Shaped(String result, List<String> pattern, Map<String, String> keys) {
            super(result);
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

        public Shapeless(String result, List<String> ingredients) {
            super(result);
            this.ingredients = ingredients;
        }

        public List<String> getIngredients() {
            return ingredients;
        }
    }

    public static class Blasting extends Cooking {
        public Blasting(String result, String ingredient, RecipeCategory category, float exp, int time){
            super(result, ingredient, category, exp, time);
        }
    }

    public static class Smelting extends Cooking{

        public Smelting(String result, String ingredient, RecipeCategory category, float exp, int time){
            super(result, ingredient, category, exp, time);
        }
    }

    public static class Smoking extends Cooking{
        public Smoking(String result, String ingredient, RecipeCategory category, float exp, int time){
            super(result, ingredient, category, exp, time);
        }
    }

    public static class Stonecutting extends RecipeDefinition{
        private final String ingredient;
        private final int resultCount;
        private final RecipeCategory category;

        public Stonecutting(String result, String ingredient, RecipeCategory category, int resultCount){
            super(result);
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


        public Smithing(String result, String template, RecipeCategory category, String base, String addition){
            super(result);
            this.template = template;
            this.category = category;
            this.base = base;
            this.addition = addition;
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

        public RecipeCategory getCategory() {
            return category;
        }
    }
}