package com.yuyuto.infinitymaxcore.entity;

import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

public class EntityValueStorage {

    //EntityID
    private final String id;

    //MOBのカテゴリー
    private MobCategory category = MobCategory.MISC;
    //当たり判定幅
    private float width = 0.6f;
    //当たり判定高さ
    private float height = 1.8f;
    //プレイヤー同期距離
    private int trackingRange = 8;
    //同期間隔(tick)
    private int updateInterval = 3;
    //火炎耐性
    private boolean fireImmune = false;
    //summonコマンドで召喚できるか
    private boolean summonable = true;
    //NBT保存をするか
    private boolean saveable = false;
    //遠距離スポーンをするかどうか
    private boolean canSpawnFarFromPlayer = false;
    //速度同期するかどうか
    private boolean velocityUpdates = true;

    //見た目系
    private EntityRendererProvider<? extends Entity> renderer;
    private ResourceLocation texture;

    //スポーン頻度
    private int spawnWeight = 0;
    //最小スポーン数と最大スポーン数
    private int minGroup = 1;
    private int maxGroup = 1;
    //出現バイオーム指定
    private List<ResourceLocation> biomes;

    //MOB限定の属性設定
    private Map<Attribute,Double> attribute = new HashMap<>();

    //Logic
    private final Map<LogicPhase, List<String>> logics = new EnumMap<>(LogicPhase.class);

    //キャッシュ
    private RegistryObject<EntityType<?>> entityType;

    /*　こっからロジック */
    public EntityValueStorage(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public MobCategory getCategory() {
        return category;
    }

    public void setCategory(MobCategory category) {
        this.category = category;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getTrackingRange() {
        return trackingRange;
    }

    public void setTrackingRange(int trackingRange) {
        this.trackingRange = trackingRange;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public boolean isFireImmune() {
        return fireImmune;
    }

    public void setFireImmune(boolean fireImmune) {
        this.fireImmune = fireImmune;
    }

    public boolean isSummonable() {
        return summonable;
    }

    public void setSummonable(boolean summonable) {
        this.summonable = summonable;
    }

    public boolean isSaveable() {
        return saveable;
    }

    public void setSaveable(boolean saveable) {
        this.saveable = saveable;
    }

    public boolean isCanSpawnFarFromPlayer() {
        return canSpawnFarFromPlayer;
    }

    public void setCanSpawnFarFromPlayer(boolean canSpawnFarFromPlayer) {
        this.canSpawnFarFromPlayer = canSpawnFarFromPlayer;
    }

    public boolean isVelocityUpdates() {
        return velocityUpdates;
    }

    public void setVelocityUpdates(boolean velocityUpdates) {
        this.velocityUpdates = velocityUpdates;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public EntityRendererProvider<? extends Entity> getRenderer() {
        return renderer;
    }

    public void setRenderer(EntityRendererProvider<?> renderer) {
        this.renderer = renderer;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public int getSpawnWeight() {
        return spawnWeight;
    }

    public void setSpawnWeight(int spawnWeight) {
        this.spawnWeight = spawnWeight;
    }

    public int getMinGroup() {
        return minGroup;
    }

    public void setMinGroup(int minGroup) {
        this.minGroup = minGroup;
    }

    public int getMaxGroup() {
        return maxGroup;
    }

    public void setMaxGroup(int maxGroup) {
        this.maxGroup = maxGroup;
    }

    public java.util.List<ResourceLocation> getBiomes() {
        return biomes;
    }

    public void setBiomes(java.util.List<ResourceLocation> biomes) {
        this.biomes = biomes;
    }

    public Map<Attribute, Double> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<Attribute, Double> attribute) {
        this.attribute = attribute;
    }

    public RegistryObject<EntityType<?>> getEntityType() {
        return entityType;
    }

    public void setEntityType(RegistryObject<EntityType<?>> entityType) {
        this.entityType = entityType;
    }

    public Map<LogicPhase, List<String>> getLogics() {
        return logics;
    }

    public void addLogic(LogicPhase phase, String logicId){
        logics.computeIfAbsent(phase,p -> new ArrayList<>()).add(logicId);
    }
}
