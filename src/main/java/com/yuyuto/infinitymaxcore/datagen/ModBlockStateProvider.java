package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.yuyuto.infinitymaxcore.registry.ModBlocks.getBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper){
        super(output, "infinitymaxcore", helper);
    }

    @Override
    protected void registerStatesAndModels(){
        for (BlockValueStorage storage : BlockStorageRegistry.getAll()){
            if (storage.getTexture() == null) continue;

            simpleBlock(
                    getBlock(storage.getBlockId()),
                    models().cubeAll(
                            storage.getBlockId(),
                            storage.getTexture()
                    )
            );
        }
    }
}
