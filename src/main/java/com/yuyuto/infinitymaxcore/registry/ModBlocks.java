package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "infinitymaxcore");
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static RegistryObject<Block> registerBlock(BlockValueStorage storage){
        return ModBlocks.BLOCKS.register(
                storage.getBlockId(),()
                -> BlockFactory.create(storage)
        );
    }
}
