package com.yuyuto.infinitymaxcore.item;

import com.yuyuto.infinitymaxcore.datagen.util.ItemModelDefinition;
import com.yuyuto.infinitymaxcore.datagen.util.RecipeDefinition;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.*;

public class ItemValueStorage {

    //item id
    private final String itemId;

    //アイテムのレアリティ
    private Rarity rarity;
    //最大スタック
    private int maxStack = 64;
    //耐久値
    private int maxDamage = 0;
    //アイテムを火に入れても消えないか
    private boolean isFireResistance = false;
    //クラフトに使用した場合に残るかどうか
    private Item craftingRemainingItem;
    //食べ物の設定
    private FoodDefinition food;
    //クリエタブ
    private String creativeTabId;

    //DataGen関係
    private ItemModelDefinition model;
    private String lang;
    private RecipeDefinition recipe;
    private String parentModel = "item/generated";

    //Logic関係
    private final Map<LogicPhase, List<String>> logics = new EnumMap<>(LogicPhase.class);

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
        if (maxDamage > 0){
            this.maxStack = 1;
        }
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

    public String getCreativeTabId() {
        return creativeTabId;
    }

    public void setCreativeTabId(String creativeTabId) {
        this.creativeTabId = creativeTabId;
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

    public String getParentModel() {
        return parentModel;
    }

    public void setParentModel(String parentModel) {
        this.parentModel = parentModel;
    }

    public Map<LogicPhase, List<String>> getLogics() {
        return logics;
    }

}
