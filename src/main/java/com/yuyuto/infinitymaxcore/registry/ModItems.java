package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,"infinitymaxcore");
    public static final Map<String,RegistryObject<Item>> ITEM_MAP = new LinkedHashMap<>();
    public static void register(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static RegistryObject<Item> registryItem(@NotNull ItemValueStorage storage){

        RegistryObject<Item> object = ModItems.ITEMS.register(
                storage.getItemId(),()
                -> ItemFactory.create(storage)
        );

        ITEM_MAP.put(storage.getItemId(),object);
        return object;
    }
}
