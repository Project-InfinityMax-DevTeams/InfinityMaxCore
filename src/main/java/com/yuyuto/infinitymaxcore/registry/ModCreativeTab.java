package com.yuyuto.infinitymaxcore.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "infinitymaxcore");
    public static void register(){
        TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = TABS.register("infinityMax",() ->
            CreativeModeTab.builder()
                    .title(Component.literal("InfinityMax"))
                    .displayItems((params,output) -> {
                        for (Item item : CreativeTabFactory.get("infinitymax")){
                            output.accept(item);
                        }
                    })
                    .build()
    );
}
