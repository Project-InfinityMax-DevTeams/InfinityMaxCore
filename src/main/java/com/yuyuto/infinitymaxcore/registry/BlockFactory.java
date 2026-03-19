package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.block.LogicBlock;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import com.yuyuto.infinitymaxcore.logic.Logic;
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

        Map<LogicPhase, List<Logic>> logicMap = new EnumMap<>(LogicPhase.class);

        storage.getLogics().forEach((phase, ids) -> {
            List<Logic> list = new ArrayList<>();

            for (String id : ids){
                Logic logic = LogicRegistry.get(id);
                if (logic != null){
                    list.add(logic);
                } else {
                    System.err.println("[InfinityMax] Logic not found: " + id);
                }
            }

            logicMap.put(phase, list);
        });

        return new LogicBlock(props, logicMap);
    }
}
