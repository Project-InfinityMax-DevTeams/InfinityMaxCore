package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.block.BlockEntityRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "infinitymaxcore");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "infinitymaxcore");
    public static final Map<String, RegistryObject<Block>> BLOCK_MAP = new LinkedHashMap<>();
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static RegistryObject<Block> registerBlock(@NotNull BlockValueStorage storage){

        RegistryObject<Block> object = ModBlocks.BLOCKS.register(
                storage.getBlockId(),()
                -> BlockFactory.create(storage)
        );

        if(storage.isHasBlockItem()){
            ModBlocks.ITEMS.register(
                    storage.getBlockId(),()
                    -> new BlockItem(object.get(), new Item.Properties())
            );
            CreativeModeTab.builder()
                    .title(Component.literal("InfinityMax"))
                    .displayItems((params, output) -> {
                       for (Item blockItem : ModCreativeTab.get("infinitymax")){
                           output.accept(blockItem);
                       }
                    });
        }

        if (storage.getBlockEntity() != null){
            BlockEntityRegistry.register(storage.getBlockEntity());
        }

        BLOCK_MAP.put(storage.getBlockId(),object);
        return object;
    }

}
