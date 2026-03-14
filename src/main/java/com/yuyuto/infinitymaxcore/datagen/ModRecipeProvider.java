package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.item.ItemStorageRegistry;
import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import com.yuyuto.infinitymaxcore.datagen.util.RecipeDefinition;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        for (ItemValueStorage storage : ItemStorageRegistry.getAll()) {

            RecipeDefinition recipe = storage.getRecipe();
            if (recipe == null) continue;

            if (recipe instanceof RecipeDefinition.Shaped shaped) {
                generateShaped(storage, shaped, consumer);
            }

            if (recipe instanceof RecipeDefinition.Shapeless shapeless) {
                generateShapeless(storage, shapeless, consumer);
            }

        }
    }

    private void generateShaped(ItemValueStorage storage, RecipeDefinition.Shaped shaped, Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()))));
        for(String row : shaped.getPattern()){
            builder.pattern(row);
        }

        shaped.getKeys().forEach((symbol, item) -> builder.define(symbol.charAt(0), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)))));

        builder.unlockedBy("has_item", has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(shaped.getKeys().values().iterator().next())))));
        builder.save(consumer);
    }

    private void generateShapeless(ItemValueStorage storage, RecipeDefinition.Shapeless shapeless, Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()))));
        for(String ingredient : shapeless.getIngredients()){
            builder.requires(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredient))));
        }

        builder.unlockedBy("has_item", has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(shapeless.getIngredients().get(0))))));
        builder.save(consumer);
    }
}