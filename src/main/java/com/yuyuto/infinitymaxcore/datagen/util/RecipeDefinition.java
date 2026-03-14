package com.yuyuto.infinitymaxcore.datagen.util;

import java.util.Map;

public class RecipeDefinition {
    private String[] pattern;
    private Map<Character, String> keys;
    private int resultCount = 1;

    public String[] getPattern() {
        return pattern;
    }

    public void setPattern(String[] pattern) {
        this.pattern = pattern;
    }

    public Map<Character, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<Character, String> keys) {
        this.keys = keys;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
