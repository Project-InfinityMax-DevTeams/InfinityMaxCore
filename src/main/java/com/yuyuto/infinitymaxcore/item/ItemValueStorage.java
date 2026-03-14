package com.yuyuto.infinitymaxcore.item;

import com.yuyuto.infinitymaxcore.datagen.util.ItemModelDefinition;
import com.yuyuto.infinitymaxcore.datagen.util.RecipeDefinition;
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
    private FoodDefinition food;
    //クリエタブ
    private String creativeTab;

    //DataGen関係
    private ItemModelDefinition model;
    private String lang;
    private RecipeDefinition recipe;

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

    public FoodDefinition getFood() {
        return food;
    }

    public void setFood(FoodDefinition food) {
        this.food = food;
    }

    public String getCreativeTab() {
        return creativeTab;
    }

    public void setCreativeTab(String creativeTab) {
        this.creativeTab = creativeTab;
    }

    public ItemModelDefinition getModel() {
        return model;
    }

    public void setModel(ItemModelDefinition model) {
        this.model = model;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public RecipeDefinition getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDefinition recipe) {
        this.recipe = recipe;
    }
}
