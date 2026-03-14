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
}