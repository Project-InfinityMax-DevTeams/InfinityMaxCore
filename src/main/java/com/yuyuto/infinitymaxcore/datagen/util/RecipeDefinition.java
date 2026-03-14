package com.yuyuto.infinitymaxcore.datagen.util;

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
        public Blasting(String ingredient, float exp, int time){
            super(ingredient, exp, time);
        }
    }

    public static class Smelting extends Cooking{

        public Smelting(String ingredient, float exp, int time){
            super(ingredient,exp,time);
        }
    }

    public static class Smoking extends Cooking{
        public Smoking(String ingredient, float exp, int time){
            super(ingredient, exp, time);
        }
    }

    public static class Stonecutting extends RecipeDefinition{
        private final String ingredient;

        public Stonecutting(String ingredient){
            this.ingredient = ingredient;
        }

        public String getIngredient() {
            return ingredient;
        }
    }

    public static class Smithing extends RecipeDefinition{

        private final String template;
        private final String base;
        private final String addition;

        public Smithing(String template, String base, String addition){
            this.template = template;
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
    }
}