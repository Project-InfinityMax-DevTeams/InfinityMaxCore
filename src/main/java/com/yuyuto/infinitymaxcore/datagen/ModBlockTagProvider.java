package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import com.yuyuto.infinitymaxcore.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper helper
    ){
        super(output, lookupProvider,"infinitymaxcore", helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider){
        for (BlockValueStorage storage : BlockStorageRegistry.getAll()){
            for (ResourceLocation tag : storage.getTags()){
                tag(BlockTags.create(tag)).add(ModBlocks.BLOCK_MAP.get(storage.getBlockId()).get());
            }
        }
    }
}
