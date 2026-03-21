package com.yuyuto.infinitymaxcore.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "infinitymaxcore");
    public static final Map<String, RegistryObject<CreativeModeTab>> TAB_MAP = new LinkedHashMap<>();
    public static void register(){
        TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static RegistryObject<CreativeModeTab> get(String tabId){
        return TAB_MAP.get(tabId);
    }

    public static void registerTab(String tabId, String title, Supplier<Item> icon){
        if (TAB_MAP.containsKey(tabId)) return;

        RegistryObject<CreativeModeTab> tab = TABS.register(tabId, () ->
                CreativeModeTab.builder()
                        .title(Component.translatable("Creativetab." + tabId))
                        .icon(() -> {
                            Item item = icon.get();
                            return item != null ? new ItemStack(item) : ItemStack.EMPTY;
                        })
                        .displayItems((params,output) -> {
                            for (Item item : CreativeTabFactory.get(tabId)){
                                output.accept(item);
                            }
                        })
                        .build()
        );

        TAB_MAP.put(tabId,tab);
    }
}
