package com.yuyuto.infinitymaxcore.recipe;

import com.yuyuto.infinitymaxcore.datagen.util.Cooking;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public abstract class RecipeDefinition {

    private final ResourceLocation result;

    protected RecipeDefinition(ResourceLocation result){
        this.result = result;
    }

    public ResourceLocation getResult() {
        return result;
    }


    public static class Shaped extends RecipeDefinition {

        private final List<String> pattern;
        private final Map<String, String> keys;

        public Shaped(ResourceLocation result, List<String> pattern, Map<String, String> keys) {
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

        public Shapeless(ResourceLocation result, List<String> ingredients) {
            super(result);
            this.ingredients = ingredients;
        }

        public List<String> getIngredients() {
            return ingredients;
        }
    }

    public static class Blasting extends Cooking {
        public Blasting(ResourceLocation result, String ingredient, RecipeCategory category, float exp, int time){
            super(result, ingredient, category, exp, time);
        }
    }

    public static class Smelting extends Cooking{

        public Smelting(ResourceLocation result, String ingredient, RecipeCategory category, float exp, int time){
            super(result, ingredient, category, exp, time);
        }
    }

    public static class Smoking extends Cooking{
        public Smoking(ResourceLocation result, String ingredient, RecipeCategory category, float exp, int time){
            super(result, ingredient, category, exp, time);
        }
    }

    public static class Stonecutting extends RecipeDefinition{
        private final String ingredient;
        private final int resultCount;
        private final RecipeCategory category;

        public Stonecutting(ResourceLocation result, String ingredient, RecipeCategory category, int resultCount){
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


        public Smithing(ResourceLocation result, String template, RecipeCategory category, String base, String addition){
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