package com.yuyuto.infinitymaxcore.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        for (RecipeDefinition recipe : RecipeRegistry.getAll()) {

            if (recipe == null) continue;

            if (recipe instanceof RecipeDefinition.Shaped shaped) {
                generateShaped(recipe, shaped, consumer);
            }

            if (recipe instanceof RecipeDefinition.Shapeless shapeless) {
                generateShapeless(recipe, shapeless, consumer);
            }

            if (recipe instanceof RecipeDefinition.Smelting smelting){
                generateSmelting(recipe,smelting,consumer);
            }

            if (recipe instanceof RecipeDefinition.Blasting blasting){
                generateBlasting(recipe, blasting, consumer);
            }

            if (recipe instanceof RecipeDefinition.Smoking smoking){
                generateSmoking(recipe, smoking, consumer);
            }

            if (recipe instanceof RecipeDefinition.Stonecutting stonecutting){
                generatedStonecutting(recipe, stonecutting, consumer);
            }

            if (recipe instanceof RecipeDefinition.Smithing smithing){
                generatedSmithing(recipe, smithing, consumer);
            }

        }
    }

    private void generateShaped(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Shaped shaped, Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()))));
        for(String row : shaped.getPattern()){
            builder.pattern(row);
        }

        shaped.getKeys().forEach((symbol, item) -> builder.define(symbol.charAt(0), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)))));

        builder.unlockedBy("has_item", has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(shaped.getKeys().values().iterator().next())))));
        builder.save(consumer);
    }

    private void generateShapeless(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Shapeless shapeless, Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()))));
        for(String ingredient : shapeless.getIngredients()){
            builder.requires(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredient))));
        }

        builder.unlockedBy("has_item", has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(shapeless.getIngredients().get(0))))));
        builder.save(consumer);
    }

    private void generateSmelting(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Smelting recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()));
        RecipeCategory category = recipe.getRecipeCategory();
        Item ingredient = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getIngredient()));

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(ingredient),
                category,
                Objects.requireNonNull(result),
                recipe.getExperience(),
                recipe.getCookingTime()
        ).unlockedBy("has_item", has(Objects.requireNonNull(ingredient))).save(consumer);
    }

    private void generateBlasting(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Blasting recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()));
        RecipeCategory category = recipe.getRecipeCategory();
        Item ingredient = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getIngredient()));

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(ingredient),
                category,
                Objects.requireNonNull(result),
                recipe.getExperience(),
                recipe.getCookingTime()
        ).unlockedBy("has_item", has(Objects.requireNonNull(ingredient))).save(consumer);
    }

    private void generateSmoking(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Smoking recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()));
        RecipeCategory category = recipe.getRecipeCategory();
        Item ingredient = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getIngredient()));

        SimpleCookingRecipeBuilder.smoking(
                Ingredient.of(ingredient),
                category,
                Objects.requireNonNull(result),
                recipe.getExperience(),
                recipe.getCookingTime()
        ).unlockedBy("has_item", has(Objects.requireNonNull(ingredient))).save(consumer);
    }

    private void generatedStonecutting(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Stonecutting recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()));
        Item ingredient = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getIngredient()));
        RecipeCategory category = recipe.getCategory();
        int resultCount = recipe.getResultCount();

        SingleItemRecipeBuilder.stonecutting(
                Ingredient.of(ingredient),
                category,
                Objects.requireNonNull(result),
                resultCount
        ).unlockedBy("has_item", has(Objects.requireNonNull(ingredient))).save(consumer);
    }

    private void generatedSmithing(@UnknownNullability RecipeDefinition storage, RecipeDefinition.Smithing recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getResult()));
        RecipeCategory category = recipe.getCategory();
        Item template = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getTemplate()));
        Item base = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getBase()));
        Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getAddition()));

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(base),
                Ingredient.of(addition),
                category,
                Objects.requireNonNull(result)
        ).unlocks("has_item", has(Objects.requireNonNull(base))).save(consumer, new ResourceLocation(recipe.getResult()));
    }
}