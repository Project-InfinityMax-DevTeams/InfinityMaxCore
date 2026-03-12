package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.*;

public class BlockValueStorage {

    //block id
    private final String blockId;

    //MapColor
    private MapColor mapColor;
    //耐爆性
    private float resistance = 6.0f;
    //固さ(破壊速度)
    private float hardness = 3.0f;
    //明るさ(光源)
    private int lightLevel = 0;
    //サウンド(SoundType)
    private SoundType soundType = SoundType.STONE;
    //最適ツール化
    private boolean requireToolForDrop = false;
    //滑りやすさ
    private float friction = 0.6f;
    //溶岩で着火するか
    private boolean ignitedByLava = false;
    //発光レンダリング(StatePredicate)
    private BlockBehaviour.StatePredicate emissiveRenderer;
    //BlockItemを作るかどうか
    private boolean hasBlockItem = true;

    //DataGen関連
    private ModelDefinition model;
    private ResourceLocation texture;
    private LootDefinition loot;
    private final List<ResourceLocation> tags = new ArrayList<>();
    private String lang;

    //BlockEntity関連
    private List<Logic> logics = new ArrayList<>();
    private RendererDefinition renderer;

    /* ---ここからメソッド--- */
    public BlockValueStorage(String id){
        this.blockId = Objects.requireNonNull(id);
    }

    public String getBlockId() {
        return blockId;
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public void setMapColor(MapColor mapColor) {
        this.mapColor = mapColor;
    }

    public float getResistance() {
        return resistance;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }

    public float getHardness() {
        return hardness;
    }

    public void setHardness(float hardness) {
        this.hardness = hardness;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
    }

    public SoundType getSoundType() {
        return soundType;
    }

    public void setSoundType(SoundType soundType) {
        this.soundType = soundType;
    }

    public boolean isRequireToolForDrop() {
        return requireToolForDrop;
    }

    public void setRequireToolForDrop(boolean requireToolForDrop) {
        this.requireToolForDrop = requireToolForDrop;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public boolean isIgnitedByLava() {
        return ignitedByLava;
    }

    public void setIgnitedByLava(boolean ignitedByLava) {
        this.ignitedByLava = ignitedByLava;
    }

    public BlockBehaviour.StatePredicate getEmissiveRenderer() {
        return emissiveRenderer;
    }

    public void setEmissiveRenderer(BlockBehaviour.StatePredicate emissiveRenderer) {
        this.emissiveRenderer = emissiveRenderer;
    }

    public boolean isHasBlockItem() {
        return hasBlockItem;
    }

    public void setHasBlockItem(boolean hasBlockItem) {
        this.hasBlockItem = hasBlockItem;
    }

    public ModelDefinition getModel() {
        return model;
    }

    public void setModel(ModelDefinition model) {
        this.model = model;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public LootDefinition getLoot() {
        return loot;
    }

    public void setLoot(LootDefinition loot) {
        this.loot = loot;
    }

    public List<ResourceLocation> getTags() {
        return tags;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<Logic> getLogics() {
        return logics;
    }

    public void setLogics(List<Logic> logics) {
        this.logics = logics;
    }

    public RendererDefinition getRenderer() {
        return renderer;
    }

    public void setRenderer(RendererDefinition renderer) {
        this.renderer = renderer;
    }
}

