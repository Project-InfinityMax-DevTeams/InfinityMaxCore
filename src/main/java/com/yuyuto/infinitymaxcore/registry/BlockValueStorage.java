package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.registry.LootDefinition;
import com.yuyuto.infinitymaxcore.registry.ModelDefinition;

import java.util.*;

public class BlockValueStorage {

    //block id
    private final String blockId;

    //MapColor(Function<BlockState,MapColor>)
    private Object mapColor;

    //当たり判定
    private boolean hasCollission;

    //サウンド(SoundType)
    private Object soundType;

    //明るさ(光源)
    private int lightLevel;

    //耐爆性
    private float resistance;

    //固さ(破壊速度)
    private float hardness;

    //最適ツール化
    private boolean requireToolForDrop;

    //ランダムティックの有無
    private boolean isRandomTick;

    //滑りやすさ
    private float friction;

    //歩き速度補正
    private float speedFactor;

    //ジャンプ力補正
    private float jumpFactor;

    //透過判定
    private boolean canOcclude;

    //溶岩で着火するか
    private boolean ignitedByLava;

    //個体強制
    private boolean forceSolidOn;

    //ピストンで押されたかどうか(PushReaction)
    private Object pushReaction;

    //破壊パーティクル
    private boolean breakParticle;

    //置き換え可能か
    private boolean replaceable;

    //LootTable指定(Supplier<ResourceLocation>)
    private Object lootTableSupplier;

    //MOBスポーン条件(StateArgumentPredicate<EntityType<?>>)
    private Object isValidSpawn;

    //導体・不導体ブロック(StatePredicate)
    private Object isRedstoneConductor;

    //窒息判定(StatePredicate)
    private Object isSuffocating;

    //視野遮断(StatePredicate)
    private Object isViewBlocking;

    //ポストプロセス(StatePredicate)
    private Object hasPostProcess;

    //発光レンダリング(StatePredicate)
    private Object emissiveRenderer;

    //形状変化可否
    private boolean dynamicShape;

    //位置補正(Optional<BlockBehavior.OffsetFunction>)
    private Object offsetFuture;

    //DataGen関連
    private ModelDefinition model;
    private LootDefinition loot;
    private final List<String> tags = new ArrayList<>();
    private final List<Logic> behaviors = new ArrayList<>();

    //アニメーションとかのレンダリング
    private String renderer;

    /* ---ここからメソッド--- */
    public BlockValueStorage(String id){
        this.blockId = Objects.requireNonNull(id);
    }
}

