package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class ModEntityLootProvider extends EntityLootSubProvider {

    public ModEntityLootProvider() {
        super(false, Stream.of()); // 基本これでOK
    }

    @Override
    public void generate() {
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()) {

            if (storage.getLoot() == null || storage.getEntityType() == null) continue;

            EntityType<?> type = storage.getEntityType().get();

            // 仮：item取得（あとでRegistryから引く）
            Item item = getItemFromId(storage.getLoot().getItemId());

            this.add(type, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(item)
                                    .setWeight(1)
                                    .apply(SetItemCountFunction.setCount(
                                            UniformGenerator.between(
                                                    storage.getLoot().getMin(),
                                                    storage.getLoot().getMax()
                                            )
                                    ))
                            )
                    )
            );
        }
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return EntityStorageRegistry.getAll().stream()
                .map(s -> s.getEntityType().get());
    }

    // 仮：あとでちゃんとRegistry化してもいい
    private Item getItemFromId(String id) {
        return net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(new net.minecraft.resources.ResourceLocation(id));
    }
}