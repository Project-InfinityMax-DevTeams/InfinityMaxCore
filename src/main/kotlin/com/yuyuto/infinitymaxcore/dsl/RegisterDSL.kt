package com.yuyuto.infinitymaxcore.dsl

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry
import com.yuyuto.infinitymaxcore.logic.LogicPhase
import com.yuyuto.infinitymaxcore.block.BlockValueStorage
import com.yuyuto.infinitymaxcore.datagen.util.LootDefinition
import com.yuyuto.infinitymaxcore.datagen.util.BlockModelDefinition
import com.yuyuto.infinitymaxcore.datagen.util.ItemModelDefinition
import com.yuyuto.infinitymaxcore.datagen.util.RecipeDefinition
import com.yuyuto.infinitymaxcore.datagen.util.RendererDefinition
import com.yuyuto.infinitymaxcore.item.FoodDefinition
import com.yuyuto.infinitymaxcore.item.ItemStorageRegistry
import com.yuyuto.infinitymaxcore.item.ItemValueStorage
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf

@DslMarker
annotation class RegisterDSL

@Suppress("DEPRECATION")
@RegisterDSL
class BlockDSLBuilder(private val storage: BlockValueStorage){

    fun hardness(value: Float){
        storage.hardness = value
    }

    fun resistance(value: Float){
        storage.resistance = value
    }

    fun light(value: Int){
        storage.lightLevel = value
    }

    fun friction(value: Float){
        storage.friction = value
    }

    fun blockIssuance(issuance: BlockBehaviour.StatePredicate){
        storage.emissiveRenderer = issuance
    }

    fun mapColor(color: MapColor){
        storage.mapColor = color
    }

    fun blockSoundType(type: SoundType){
        storage.soundType = type
    }

    fun breakingToolRequire(required: Boolean){
        storage.isRequireToolForDrop = required
    }

    fun doLavaFire(lavaFire: Boolean){
        storage.isIgnitedByLava = lavaFire
    }

    fun hasBlockItem(created: Boolean){
        storage.isHasBlockItem = created
    }

    fun creativeTab(value: String){
        storage.creativeTabId = value
    }

    fun model(model: BlockModelDefinition){
        storage.model = model
    }

    fun loot(loot: LootDefinition){
        storage.loot = loot
    }

    fun tag(id: String){
        storage.addTag(ResourceLocation(id))
    }

    fun lang(text: String){
        storage.lang = text
    }

    fun renderer(def: RendererDefinition){
        storage.renderer = def
    }

    fun texture(id: String){
        storage.texture = ResourceLocation(id)
    }

    fun tick(logic: String){
        storage.addLogic(LogicPhase.TICK, logic)
    }

    fun use(logic: String){
        storage.addLogic(LogicPhase.USE,logic)
    }

    fun randomTick(logic: String){
        storage.addLogic(LogicPhase.RANDOM_TICK,logic)
    }

}

fun block(id: String, init: BlockDSLBuilder.() -> Unit): BlockValueStorage{
    val storage = BlockValueStorage(id)
    val builder = BlockDSLBuilder(storage)

    builder.init()
    BlockStorageRegistry.register(storage)
    return storage
}

@RegisterDSL
class ItemDSLBuilder(private val storage: ItemValueStorage){
    fun rarity(rarity: Rarity){
        storage.rarity = rarity
    }

    fun maxStack(maxStack: Int){
        storage.maxStack = maxStack
    }

    fun maxDamage(maxDamage: Int){
        storage.maxDamage = maxDamage
    }

    fun fireResistance(resistance: Boolean){
        storage.isFireResistance = resistance
    }

    fun craftingRemainingItem(remainingItem: Item){
        storage.craftingRemainingItem = remainingItem
    }

    fun food(block: FoodScope.() -> Unit){
        val scope = FoodScope()
        scope.block()
        storage.food = scope.build()
    }

    fun creativeTab(creativeTab: String){
        storage.creativeTabId = creativeTab
    }

    fun model(model: ItemModelDefinition){
        storage.model = model
    }

    fun recipe(block: RecipeScope.() -> Unit){
        val scope = RecipeScope()
        scope.block()
        storage.recipe = scope.build()
    }

    fun lang(lang: String){
        storage.lang = lang
    }

    fun parentModel(value: String){
        storage.parentModel = value
    }

    //Logicはまだ未定なのでここでは割愛。
    //ここでいうLogicはアイテムに埋め込むゲーム処理のことを言う。
    //例:銃火器系、魔法弾発射の杖、マシンリペアツールなど
}

fun item(id: String, init: ItemDSLBuilder.() -> Unit): ItemValueStorage{
    val storage = ItemValueStorage(id)
    val scope = ItemDSLBuilder(storage)
    scope.init()

    ItemStorageRegistry.register(storage)
    return storage
}

@RegisterDSL
class FoodScope{
    private val food = FoodDefinition()

    fun nutrition(value: Int){
        food.nutrition = value
    }

    fun saturation(value: Float){
        food.saturation = value
    }

    fun alwaysEat(value: Boolean){
        food.isAlwaysEat = value
    }

    fun meat(value: Boolean){
        food.isMeat = value
    }

    fun fastFood(value: Boolean){
        food.isFastFood = value
    }

    fun build(): FoodDefinition{
        return food
    }
}

@RegisterDSL
class RecipeScope{
    private var recipe: RecipeDefinition? = null

    fun shaped(block: ShapedRecipeScope.() -> Unit){
        val scope = ShapedRecipeScope()
        scope.block()
        recipe = scope.build()
    }

    fun shapeless(block: ShapelessRecipeScope.() -> Unit){
        val scope = ShapelessRecipeScope()
        scope.block()
        recipe = scope.build()
    }

    fun smelting(block: SmeltingRecipeScope.() -> Unit){
        val scope = SmeltingRecipeScope()
        scope.block()
        recipe = scope.build()
    }

    fun blasting(block: BlastingRecipeScope.() -> Unit){
        val scope = BlastingRecipeScope()
        scope.block()
        recipe = scope.build()
    }

    fun smoking(block: SmokingRecipeScope.() -> Unit){
        val scope = SmokingRecipeScope()
        scope.block()
        recipe = scope.build()
    }

    fun stonecutting(block: StonecuttingRecipeScope.() -> Unit){
        val scope = StonecuttingRecipeScope()
        scope.build()
        recipe = scope.build()
    }

    fun smithing(block: SmithingRecipeScope.() -> Unit){
        val scope = SmithingRecipeScope()
        scope.build()
        recipe = scope.build()
    }

    fun build(): RecipeDefinition{
        return recipe ?: throw IllegalStateException("recipe not defined")
    }
}

class ShapedRecipeScope {

    private val pattern = mutableListOf<String>()
    private val keys = mutableMapOf<String, String>()

    fun pattern(vararg rows: String){
        pattern.addAll(rows)
    }

    fun key(symbol: String, item: String){
        keys[symbol] = item
    }

    fun build(): RecipeDefinition {
        return RecipeDefinition.Shaped(pattern, keys)
    }
}

class ShapelessRecipeScope {

    private val ingredients = mutableListOf<String>()

    fun ingredient(item: String){
        ingredients.add(item)
    }

    fun build(): RecipeDefinition {
        return RecipeDefinition.Shapeless(ingredients)
    }
}

class SmeltingRecipeScope{

    private var ingredient: String = ""
    private var experience: Float = 0f
    private var category: RecipeCategory = RecipeCategory.MISC
    private var cookingTime: Int = 200

    fun ingredient(id: String){
        ingredient = id
    }
    fun experience(value: Float){
        experience = value
    }
    fun category(value: RecipeCategory){
        category = value
    }
    fun cookingTime(value: Int){
        cookingTime = value
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Smelting(ingredient, category, experience, cookingTime)
    }

}

class BlastingRecipeScope{

    private var ingredient: String = ""
    private var experience: Float = 0f
    private var category: RecipeCategory = RecipeCategory.MISC
    private var cookingTime: Int = 200

    fun ingredient(id: String){
        ingredient = id
    }
    fun experience(value: Float){
        experience = value
    }
    fun category(value: RecipeCategory){
        category = value
    }
    fun cookingTime(value: Int){
        cookingTime = value
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Blasting(ingredient, category, experience, cookingTime)
    }

}

class SmokingRecipeScope{

    private var ingredient: String = ""
    private var experience: Float = 0f
    private var category: RecipeCategory = RecipeCategory.MISC
    private var cookingTime: Int = 200

    fun ingredient(id: String){
        ingredient = id
    }
    fun experience(value: Float){
        experience = value
    }
    fun category(value: RecipeCategory){
        category = value
    }
    fun cookingTime(value: Int){
        cookingTime = value
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Smoking(ingredient, category, experience, cookingTime)
    }

}

class StonecuttingRecipeScope{

    private var ingredient: String = ""
    private var category: RecipeCategory = RecipeCategory.MISC
    private var resultCount: Int = 2

    fun ingredient(id: String){
        ingredient = id
    }
    fun category(value: RecipeCategory){
        category = value
    }
    fun resultCount(value: Int){
        resultCount = value
    }
    fun build(): RecipeDefinition{
        return RecipeDefinition.Stonecutting(ingredient, category, resultCount)
    }
}

class SmithingRecipeScope{

    private var template: String = ""
    private var category: RecipeCategory = RecipeCategory.MISC
    private var base: String = ""
    private var addition: String = ""
    private var result: String = ""

    fun template(id: String){
        template = id
    }
    fun category(value: RecipeCategory){
        category = value
    }
    fun base(id: String){
        base = id
    }
    fun addition(id: String){
        addition = id
    }
    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Smithing(template, category, base, addition, result)
    }
}
