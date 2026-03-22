package com.yuyuto.infinitymaxcore.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ModBiomeModifierProvider implements DataProvider {

    private final PackOutput output;

    public ModBiomeModifierProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {

        var futures = new java.util.ArrayList<CompletableFuture<?>>();

        for (EntityValueStorage storage : EntityStorageRegistry.getAll()) {

            if (storage.getBiomes() == null || storage.getBiomes().isEmpty()) continue;

            JsonObject json = new JsonObject();

            // type
            json.addProperty("type", "forge:add_spawns");

            // biomes
            JsonArray biomes = new JsonArray();
            storage.getBiomes().forEach(b -> biomes.add(b.toString()));
            json.add("biomes", biomes);

            // spawner
            JsonObject spawner = new JsonObject();
            spawner.addProperty("type", "infinitymaxcore:" + storage.getId());
            spawner.addProperty("weight", storage.getSpawnWeight());
            spawner.addProperty("minCount", storage.getMinGroup());
            spawner.addProperty("maxCount", storage.getMaxGroup());

            json.add("spawners", spawner);

            // 出力パス
            Path path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                    .resolve("data/infinitymaxcore/forge/biome_modifier/" + storage.getId() + ".json");

            futures.add(DataProvider.saveStable(cache, json, path));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public @NotNull String getName() {
        return "InfinityMax BiomeModifier";
    }
}