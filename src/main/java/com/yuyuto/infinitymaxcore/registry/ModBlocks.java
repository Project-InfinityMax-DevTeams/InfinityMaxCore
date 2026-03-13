package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "infinitymaxcore");
    public static final Map<String, RegistryObject<Block>> BLOCK_MAP = new HashMap<>();
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static RegistryObject<Block> registerBlock(BlockValueStorage storage){

        RegistryObject<Block> object = ModBlocks.BLOCKS.register(
                storage.getBlockId(),()
                -> BlockFactory.create(storage)
        );

        BLOCK_MAP.put(storage.getBlockId(),object);
        return object;
    }

    public static Block getBlock(String id){
        return BLOCK_MAP.get(id).get();
    }
}
