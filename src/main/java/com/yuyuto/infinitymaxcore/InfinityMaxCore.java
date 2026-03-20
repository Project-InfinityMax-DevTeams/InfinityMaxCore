package com.yuyuto.infinitymaxcore;

import com.mojang.logging.LogUtils;
import com.yuyuto.infinitymaxcore.datagen.*;
import com.yuyuto.infinitymaxcore.recipe.ModRecipeProvider;
import com.yuyuto.infinitymaxcore.registry.ModBlocks;
import com.yuyuto.infinitymaxcore.registry.ModCreativeTab;
import com.yuyuto.infinitymaxcore.registry.ModEntities;
import com.yuyuto.infinitymaxcore.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

// この値は、META-INF/mods.toml ファイル内のエントリと一致する必要があります。
@Mod(InfinityMaxCore.MODID)
public class InfinityMaxCore {
    // すべての参照元が参照できるように、共通の場所でMODIDを定義する
    public static final String MODID = "infinitymaxcore";
    // SLF4Jロガーを直接参照する
    private static final Logger LOGGER = LogUtils.getLogger();

    //登録
    public InfinityMaxCore(){
        ModBlocks.register();
        ModItems.register();
        ModEntities.register();
        ModCreativeTab.register();
    }

    //DataGen
    @SubscribeEvent
    public static void gatherData(@NotNull GatherDataEvent event){

        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();

        gen.addProvider(event.includeClient(), new ModBlockStateProvider(output, event.getExistingFileHelper()));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(output));
        gen.addProvider(event.includeClient(), new ModLangProvider(output));
        gen.addProvider(event.includeServer(), new ModBlockTagProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(output));
    }

}
