package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModBiomeModifierProvider extends DatapackBuiltinEntriesProvider {

    public ModBiomeModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup){
        super(output,lookup, Set.of("infinitymaxcore"));
    }

    @Override
    protected void add(HolderLookup.Provider provider){
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){
            if (storage.getBiomes() == null)continue;

            var biomeRegistry = provider.lookupOrThrow(Registries.BIOME);
            var entityRegistry = provider.lookupOrThrow(Registries.ENTITY_TYPE);

            HolderSet.Named biomes = HolderSet.direct(
                    storage.getBiomes().stream()
                            .map(id -> biomeRegistry.getOrThrow(ResourceKey.create(Registries.BIOME,id)))
                            .toList()
            );

            var entity = entityRegistry.getOrThrow(
                    ResourceKey.create(Registries.ENTITY_TYPE,new ResourceLocation("infinitymaxcore", storage.getId()))
            );

            BiomeModifier modifier = new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                    biomes,
                    List.of(new MobSpawnSettings.SpawnerData(
                            entity,
                            storage.getSpawnWeight(),
                            storage.getMinGroup(),
                            storage.getMaxGroup()
                    ))
            );

            this.builder.add(ResourceKey.create(ForgeBiomeModifiers.Keys.ADD_SPAWNS, new ResourceLocation("infinitymaxcore", storage.getId())),
                    modifier
            );
        }
    }
}
