package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

import static com.yuyuto.infinitymaxcore.registry.ModBlocks.getBlock;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public ModBlockLootProvider(){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate(){
        for(BlockValueStorage storage : BlockStorageRegistry.getAll()){
            dropSelf(getBlock(storage.getBlockId()));
        }
    }
}
