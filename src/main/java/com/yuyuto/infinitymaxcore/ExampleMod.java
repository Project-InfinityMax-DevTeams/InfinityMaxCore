package com.yuyuto.infinitymaxcore;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// この値は、META-INF/mods.toml ファイル内のエントリと一致する必要があります。
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // すべての参照元が参照できるように、共通の場所でMODIDを定義する
    public static final String MODID = "infinitymaxcore";
    // SLF4Jロガーを直接参照する
    private static final Logger LOGGER = LogUtils.getLogger();
    // 「examplemod」名前空間の下に登録されるブロックを保持するための遅延登録を作成する
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // 「examplemod」名前空間の下に登録されるアイテムを保持するための遅延登録を作成する
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // 「examplemod」の下に登録されるCreativeModeTabsを保持するための遅延登録を作成する namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // 名前空間とパスを組み合わせて、ID「examplemod:example_block」を持つ新しいブロックを作成します
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    // 名前空間とパスを組み合わせて、ID「examplemod:example_block」を持つ新しいBlockItemを作成します
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    // ID「examplemod:example_id」、栄養値1、満腹度2の新しい食品アイテムを作成します
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    // 例アイテム用にID「examplemod:example_tab」のクリエイティブタブを作成し、戦闘タブの後に配置する
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // 例示項目をタブに追加します。独自のタブについては、この方法がイベントよりも推奨されます。
            }).build());

    public ExampleMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // modloading用のcommonSetupメソッドを登録する
        modEventBus.addListener(this::commonSetup);

        // 遅延レジスタをmodイベントバスに登録し、ブロックが登録されるようにする
        BLOCKS.register(modEventBus);
        // 遅延レジスタをmodイベントバスに登録し、アイテムが登録されるようにする
        ITEMS.register(modEventBus);
        // 遅延レジスタをmodイベントバスに登録し、タブが登録されるようにする
        CREATIVE_MODE_TABS.register(modEventBus);

        // サーバーやその他のゲームイベントで興味のあるものに自分たちで登録する
        MinecraftForge.EVENT_BUS.register(this);

        // アイテムをクリエイティブタブに登録する
        modEventBus.addListener(this::addCreative);

        // Forgeが設定ファイルを作成・読み込めるよう、当MODのForgeConfigSpecを登録してください
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // 一般的な設定コード
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    //Example Block項目をビルディングブロックタブに追加する
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // SubscribeEvent を使用し、イベントバスに呼び出すメソッドを発見させることができます
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // サーバー起動時に何かを行う
        LOGGER.info("HELLO from server starting");
    }

    // EventBusSubscriberを使用すると、@SubscribeEventアノテーションが付与されたクラス内のすべての静的メソッドを自動的に登録できます。
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // クライアント設定コードの一部
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
