package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.block.EntityBlockImpl;
import com.yuyuto.infinitymaxcore.block.LogicBlock;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicMapper;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.LogicRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BlockFactory {
    @Contract("_ -> new")
    public static @NotNull Block create(@NotNull BlockValueStorage storage){

        BlockBehaviour.Properties props = BlockBehaviour.Properties
                .of()
                .mapColor(storage.getMapColor())
                .strength(storage.getHardness(), storage.getResistance())
                .friction(storage.getFriction())
                .lightLevel(s -> storage.getLightLevel())
                .sound(storage.getSoundType());

        if (storage.isRequireToolForDrop()){
            props.requiresCorrectToolForDrops();
        }

        if (storage.isIgnitedByLava()){
            props.ignitedByLava();
        }

        if (storage.getEmissiveRenderer() != null){
            props.emissiveRendering(storage.getEmissiveRenderer());
        }

        Map<LogicPhase, List<Logic>> logicMap = LogicMapper.map(storage.getLogics());

        if (storage.getBlockEntity() != null){
            return new EntityBlockImpl<>(props, logicMap, storage);
        }

        return new LogicBlock(props, logicMap);
    }
}
