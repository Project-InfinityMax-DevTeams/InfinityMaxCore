package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import com.yuyuto.infinitymaxcore.registry.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public ModBlockLootProvider(){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate(){
        for(BlockValueStorage storage : BlockStorageRegistry.getAll()){

            switch (storage.getLoot()){
                case SELF -> dropSelf(ModBlocks.BLOCK_MAP.get(storage.getBlockId()).get());
                case SILK_ONLY -> dropWhenSilkTouch(ModBlocks.BLOCK_MAP.get(storage.getBlockId()).get());
                case NONE -> {/* Noneはドロップをしないという設定のため、確実に空実装になる。 */}
            }
        }
    }
}
