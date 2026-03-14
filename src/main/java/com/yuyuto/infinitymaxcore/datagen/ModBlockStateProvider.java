package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import com.yuyuto.infinitymaxcore.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper){
        super(output, "infinitymaxcore", helper);
    }

    @Override
    protected void registerStatesAndModels(){
        for (BlockValueStorage storage : BlockStorageRegistry.getAll()){
            if (storage.getTexture() == null) continue;

            switch (storage.getModel()){

                case CUBE -> simpleBlock(
                        ModBlocks.BLOCK_MAP.get(storage.getBlockId()).get(),
                        models().cubeAll(storage.getBlockId(), storage.getTexture())
                );

                case PILLAR -> axisBlock(
                        (RotatedPillarBlock)ModBlocks.BLOCK_MAP.get(storage.getBlockId()).get(),
                        storage.getTexture(),
                        storage.getTexture()
                );
            }
        }
    }
}
