package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.datagen.util.EntityLootDefinition;
import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Stream;

public class ModEntityLootProvider extends EntityLootSubProvider {

    public ModEntityLootProvider() {
        super(FeatureFlags.REGISTRY.allFlags()); // 基本これでOK
    }

    @Override
    public void generate() {
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()) {

            if (storage.getLoots().isEmpty() || storage.getEntityType() == null) continue;

            EntityType<?> type = storage.getEntityType().get();

            LootPool.Builder pool = LootPool.lootPool();

            for (EntityLootDefinition loot : storage.getLoots()) {

                Item item = ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation(loot.itemId())
                );

                pool.add(
                        LootItem.lootTableItem(Objects.requireNonNull(item))
                                .setWeight(loot.weight())
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(
                                                loot.min(),
                                                loot.max()
                                        )
                                ))
                                .when(LootItemRandomChanceCondition.randomChance(loot.chance()))
                );
            }

            this.add(type, LootTable.lootTable().withPool(pool));
        }
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return EntityStorageRegistry.getAll().stream()
                .map(s -> s.getEntityType().get());
    }
}