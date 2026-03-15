package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.block.LogicBlock;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockFactory {
    public static Block create(BlockValueStorage storage){

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

        return new LogicBlock(props, storage);
    }
}
