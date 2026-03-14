package com.yuyuto.infinitymaxcore.item;

import com.yuyuto.infinitymaxcore.registry.util.RecipeDefinition;
import com.yuyuto.infinitymaxcore.registry.util.ModelDefinition;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Objects;

public class ItemValueStorage {

    //item id
    private final String itemId;

    //アイテムのレアリティ
    private Rarity rarity;
    //最大スタック
    private int maxStack = 64;
    //耐久値
    private int maxDamage;
    //アイテムを火に入れても消えないか
    private boolean isFireResistance = true;
    //クラフトに使用した場合に残るかどうか
    private Item craftingRemainingItem;
    //食べ物の設定
    private FoodProperties foodProperties;

    //DataGen関係
    private ModelDefinition model;
    private String lang;
    private RecipeDefinition recipeDefinition;

    /* -----ここからメソッド----- */
    public ItemValueStorage(String id){
        this.itemId = Objects.requireNonNull(id);
    }
    public String getItemId() {
        return itemId;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public boolean isFireResistance() {
        return isFireResistance;
    }

    public void setFireResistance(boolean fireResistance) {
        isFireResistance = fireResistance;
    }

    public Item getCraftingRemainingItem() {
        return craftingRemainingItem;
    }

    public void setCraftingRemainingItem(Item craftingRemainingItem) {
        this.craftingRemainingItem = craftingRemainingItem;
    }

    public FoodProperties getFoodProperties() {
        return foodProperties;
    }

    public void setFoodProperties(FoodProperties foodProperties) {
        this.foodProperties = foodProperties;
    }

    public ModelDefinition getModel() {
        return model;
    }

    public void setModel(ModelDefinition model) {
        this.model = model;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public RecipeDefinition getRecipeDefinition() {
        return recipeDefinition;
    }

    public void setRecipeDefinition(RecipeDefinition recipeDefinition) {
        this.recipeDefinition = recipeDefinition;
    }
}
