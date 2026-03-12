package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
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
    private Object emissiveRenderer;

    //DataGen関連
    private ModelDefinition model;
    private ResourceLocation texture;
    private LootDefinition loot;
    private final List<String> tags = new ArrayList<>();
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
}

