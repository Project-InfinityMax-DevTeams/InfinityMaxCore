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

        for (RecipeDefinition recipeDef : RecipeRegistry.getAll()) {

            if (recipe == null) continue;

            if (recipe instanceof RecipeDefinition.Shaped shaped) {
                generateShaped(recipeDef, shaped, consumer);
            }

            if (recipe instanceof RecipeDefinition.Shapeless shapeless) {
                generateShapeless(recipeDef, shapeless, consumer);
            }

            if (recipe instanceof RecipeDefinition.Smelting smelting){
                generateSmelting(recipeDef,smelting,consumer);
            }

            if (recipe instanceof RecipeDefinition.Blasting blasting){
                generateBlasting(recipeDef, blasting, consumer);
            }

            if (recipe instanceof RecipeDefinition.Smoking smoking){
                generateSmoking(recipeDef, smoking, consumer);
            }

            if (recipe instanceof RecipeDefinition.Stonecutting stonecutting){
                generatedStonecutting(recipeDef, stonecutting, consumer);
            }

            if (recipe instanceof RecipeDefinition.Smithing smithing){
                generatedSmithing(recipeDef, smithing, consumer);
            }

        }
    }

    private void generateShaped(@UnknownNullability ItemValueStorage storage, RecipeDefinition.Shaped shaped, Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()))));
        for(String row : shaped.getPattern()){
            builder.pattern(row);
        }

        shaped.getKeys().forEach((symbol, item) -> builder.define(symbol.charAt(0), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)))));

        builder.unlockedBy("has_item", has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(shaped.getKeys().values().iterator().next())))));
        builder.save(consumer);
    }

    private void generateShapeless(@UnknownNullability ItemValueStorage storage, RecipeDefinition.Shapeless shapeless, Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()))));
        for(String ingredient : shapeless.getIngredients()){
            builder.requires(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredient))));
        }

        builder.unlockedBy("has_item", has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(shapeless.getIngredients().get(0))))));
        builder.save(consumer);
    }

    private void generateSmelting(@UnknownNullability ItemValueStorage storage, RecipeDefinition.Smelting recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()));
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

    private void generateBlasting(@UnknownNullability ItemValueStorage storage, RecipeDefinition.Blasting recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()));
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

    private void generateSmoking(@UnknownNullability ItemValueStorage storage, RecipeDefinition.Smoking recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()));
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

    private void generatedStonecutting(ItemValueStorage storage, RecipeDefinition.Stonecutting recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()));
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

    private void generatedSmithing(ItemValueStorage storage, RecipeDefinition.Smithing recipe, Consumer<FinishedRecipe> consumer){
        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storage.getItemId()));
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