package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.*;
import java.util.function.Function;

public class BlockValueStorage {

    //block id
    private final String blockId;

    //MapColor(Function<BlockState,MapColor>)
    private Function<BlockState, MapColor> mapColor;
    //耐爆性
    private float resistance;
    //固さ(破壊速度)
    private float hardness;
    //明るさ(光源)
    private int lightLevel;
    //サウンド(SoundType)
    private SoundType soundType;
    //最適ツール化
    private boolean requireToolForDrop;
    //滑りやすさ
    private float friction;
    //溶岩で着火するか
    private boolean ignitedByLava;
    //発光レンダリング(StatePredicate)
    private BlockBehaviour.StatePredicate emissiveRenderer;

    //DataGen関連
    private ModelDefinition model;
    private ResourceLocation texture;
    private LootDefinition loot;
    private final List<ResourceLocation> tags = new ArrayList<>();
    private String lang;
    private List<Logic> logics = new ArrayList<>();

    //アニメーションとかのレンダリング
    private RendererDefinition renderer;

    /* ---ここからメソッド--- */
    public BlockValueStorage(String id){
        this.blockId = Objects.requireNonNull(id);
    }

    public String getBlockId() {
        return blockId;
    }

    public List<Logic> getLogics() {
        return logics;
    }

    public void setLogics(List<Logic> logics) {
        this.logics = logics;
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

    public RendererDefinition getRenderer() {
        return renderer;
    }

    public void setRenderer(RendererDefinition renderer) {
        this.renderer = renderer;
    }
}

