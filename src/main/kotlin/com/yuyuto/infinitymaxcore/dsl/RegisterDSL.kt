package com.yuyuto.infinitymaxcore.dsl

import com.mojang.datafixers.types.Type
import com.yuyuto.infinitymaxcore.block.BaseBlockEntity
import com.yuyuto.infinitymaxcore.block.BlockEntityStorage
import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry
import com.yuyuto.infinitymaxcore.block.BlockValueStorage
import com.yuyuto.infinitymaxcore.datagen.util.BlockModelDefinition
import com.yuyuto.infinitymaxcore.datagen.util.ItemModelDefinition
import com.yuyuto.infinitymaxcore.datagen.util.BlockLootDefinition
import com.yuyuto.infinitymaxcore.datagen.util.EntityLootDefinition
import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage
import com.yuyuto.infinitymaxcore.item.FoodDefinition
import com.yuyuto.infinitymaxcore.item.ItemStorageRegistry
import com.yuyuto.infinitymaxcore.item.ItemValueStorage
import com.yuyuto.infinitymaxcore.logic.LogicPhase
import com.yuyuto.infinitymaxcore.recipe.RecipeDefinition
import com.yuyuto.infinitymaxcore.recipe.RecipeRegistry
import com.yuyuto.infinitymaxcore.registry.ModBlocks
import com.yuyuto.infinitymaxcore.registry.ModCreativeTab
import com.yuyuto.infinitymaxcore.registry.ModEntities
import com.yuyuto.infinitymaxcore.registry.ModItems
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

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
        storage.creativeTabKey = value
    }

    fun blockEntity(init: BlockEntityBuilder<BlockEntity>.() -> Unit){
        val bestorage = BlockEntityStorage<BlockEntity>(storage.blockId)

        val builder = BlockEntityBuilder(bestorage)
        builder.init()

        if (bestorage.getSupplier() == null){
            bestorage.setSupplier { pos, state ->
                BaseBlockEntity(pos, state, bestorage)
            }
        }

        storage.blockEntity = bestorage
    }

    fun model(model: BlockModelDefinition){
        storage.model = model
    }

    fun loot(loot: BlockLootDefinition){
        storage.loot = loot
    }

    fun tag(id: String){
        storage.addTag(ResourceLocation(id))
    }

    fun lang(text: String){
        storage.lang = text
    }

    fun texture(id: String){
        storage.texture = ResourceLocation(id)
    }

    fun blockTick(logic: String){
        storage.addLogic(LogicPhase.BLOCK_TICK, logic)
    }

    fun blockUse(logic: String){
        storage.addLogic(LogicPhase.BLOCK_USE,logic)
    }
    fun blockRandomTick(logic: String){
        storage.addLogic(LogicPhase.BLOCK_RANDOM_TICK,logic)
    }
    fun blockNeighborUpdate(logic: String){
        storage.addLogic(LogicPhase.BLOCK_NEIGHBOR_UPDATE,logic)
    }
    fun blockEntityInside(logic: String){
        storage.addLogic(LogicPhase.BLOCK_ENTITY_INSIDE,logic)
    }
    fun blockStepOn(logic: String){
        storage.addLogic(LogicPhase.BLOCK_STEP_ON, logic)
    }

}

fun block(id: String, init: BlockDSLBuilder.() -> Unit): BlockValueStorage{
    val storage = BlockValueStorage(id)
    val builder = BlockDSLBuilder(storage)

    builder.init()
    BlockStorageRegistry.register(storage)
    ModBlocks.registerBlock(storage)
    return storage
}

class BlockEntityBuilder<T : BlockEntity>(private val storage: BlockEntityStorage<T>){

    fun supplier(supplier: BlockEntityType.BlockEntitySupplier<T>){
        storage.supplier = supplier
    }

    fun blocks(blocks: List<Block>){
        storage.blocks = blocks
    }

    fun dataType(types: Type<*>){
        storage.dataType = types
    }

    fun ticker(tick: BlockEntityTicker<T>){
        storage.ticker = tick
    }

    fun renderer(renderer: BlockEntityRenderer<T>){
        storage.renderer = renderer
    }

    fun gui(gui: MenuType<*>){
        storage.menu = gui
    }

    fun packetSync(){
        storage.isSync = true
    }
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

    fun lang(lang: String){
        storage.lang = lang
    }

    fun parentModel(value: String){
        storage.parentModel = value
    }

    fun itemUse(logic: String){
        storage.addLogic(LogicPhase.ITEM_USE,logic)
    }
    fun itemTick(logic: String){
        storage.addLogic(LogicPhase.ITEM_TICK,logic)
    }
    fun itemInventoryTick(logic: String){
        storage.addLogic(LogicPhase.ITEM_INVENTORY_TICK,logic)
    }
    fun itemReleaseUse(logic: String){
        storage.addLogic(LogicPhase.ITEM_RELEASE_USE,logic)
    }
}

fun item(id: String, init: ItemDSLBuilder.() -> Unit): ItemValueStorage{
    val storage = ItemValueStorage(id)
    val scope = ItemDSLBuilder(storage)
    scope.init()

    ItemStorageRegistry.register(storage)
    ModItems.registryItem(storage)
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
class EntityDSLBuilder(private val storage: EntityValueStorage){

    fun category(value: MobCategory){
        storage.category = value
    }

    fun width(value: Float){
        storage.width = value
    }

    fun height(value: Float){
        storage.height = value
    }

    fun trackingRange(value: Int){
        storage.trackingRange = value
    }

    fun updateInterval(value: Int){
        storage.updateInterval = value
    }

    fun fireImmune(){
        storage.isFireImmune = true
    }

    fun noSummonCommandToSpawn(){
        storage.isSummonable = false
    }

    fun saveable(){
        storage.isSaveable = true
    }

    fun spawnFarFromPlayer(){
        storage.isCanSpawnFarFromPlayer = true
    }

    fun noVelocityUpdate(){
        storage.isVelocityUpdates = false
    }

    fun loot(block: LootScope.() -> Unit){
        val scope = LootScope(storage)
        scope.block()
    }

    fun renderer(value: EntityRendererProvider<out Entity>){
        storage.renderer = value
    }

    fun texture(id: String){
        storage.texture = ResourceLocation(id)
    }

    fun spawnWeight(value: Int){
        storage.spawnWeight = value
    }

    fun spawnGroup(min: Int, max: Int){
        storage.minGroup = min
        storage.maxGroup = max
    }

    fun spawnBiomes(value: List<ResourceLocation>){
        storage.biomes = value
    }

    fun lang(value: String){
        storage.lang = value
    }

    fun mobAttribute(value: MutableMap<Attribute, Double>){
        storage.attribute = value
    }

    fun entityInteract(logic: String) {
        storage.addLogic(LogicPhase.ENTITY_INTERACT, logic)
    }
    fun entityTick(logic: String){
        storage.addLogic(LogicPhase.ENTITY_TICK,logic)
    }
    fun entityHurt(logic: String){
        storage.addLogic(LogicPhase.ENTITY_HURT,logic)
    }
    fun entityAttack(logic: String){
        storage.addLogic(LogicPhase.ENTITY_ATTACK,logic)
    }
}

fun entity(id: String, init: EntityDSLBuilder.() -> Unit): EntityValueStorage{
    val storage = EntityValueStorage(id)
    val scope = EntityDSLBuilder(storage)
    scope.init()

    EntityStorageRegistry.register(storage)
    ModEntities.registerEntity(storage)
    return storage
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
        scope.block()
        recipe = scope.build()
    }

    fun smithing(block: SmithingRecipeScope.() -> Unit){
        val scope = SmithingRecipeScope()
        scope.block()
        recipe = scope.build()
    }

    fun build(): RecipeDefinition{
        val r =  recipe ?: throw IllegalStateException("recipe not defined")
        return r
    }
}
fun recipe(block: RecipeScope.() -> Unit){
    val scope = RecipeScope()
    scope.block()

    RecipeRegistry.register(scope.build())
}

class ShapedRecipeScope {

    private val pattern = mutableListOf<String>()
    private val keys = mutableMapOf<String, String>()
    private var result: String = ""

    fun pattern(vararg rows: String){
        pattern.addAll(rows)
    }

    fun key(symbol: String, item: String){
        keys[symbol] = item
    }

    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition {
        return RecipeDefinition.Shaped(result,pattern, keys)
    }
}

class ShapelessRecipeScope {

    private val ingredients = mutableListOf<String>()
    private var result: String = ""

    fun ingredient(item: String){
        ingredients.add(item)
    }

    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition {
        return RecipeDefinition.Shapeless(result, ingredients)
    }
}

class SmeltingRecipeScope{

    private var ingredient: String = ""
    private var experience: Float = 0f
    private var category: RecipeCategory = RecipeCategory.MISC
    private var cookingTime: Int = 200
    private var result: String = ""

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
    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Smelting(result, ingredient, category, experience, cookingTime)
    }

}

class BlastingRecipeScope{

    private var ingredient: String = ""
    private var experience: Float = 0f
    private var category: RecipeCategory = RecipeCategory.MISC
    private var cookingTime: Int = 200
    private var result: String = ""

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
    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Blasting(result, ingredient, category, experience, cookingTime)
    }

}

class SmokingRecipeScope{

    private var ingredient: String = ""
    private var experience: Float = 0f
    private var category: RecipeCategory = RecipeCategory.MISC
    private var cookingTime: Int = 200
    private var result: String = ""

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
    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Smoking(result, ingredient, category, experience, cookingTime)
    }

}

class StonecuttingRecipeScope{

    private var ingredient: String = ""
    private var category: RecipeCategory = RecipeCategory.MISC
    private var resultCount: Int = 2
    private var result: String = ""

    fun ingredient(id: String){
        ingredient = id
    }
    fun category(value: RecipeCategory){
        category = value
    }
    fun resultCount(value: Int){
        resultCount = value
    }
    fun result(id: String){
        result = id
    }

    fun build(): RecipeDefinition{
        return RecipeDefinition.Stonecutting(result, ingredient, category, resultCount)
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
        return RecipeDefinition.Smithing(result, template, category, base, addition)
    }
}

class LootScope(private val storage: EntityValueStorage){
    fun drop(
        item: String,
        min: Int,
        max: Int,
        weight: Int,
        chance: Float
        ){
        storage.addLoot(EntityLootDefinition(item,min,max,weight,chance))
    }
}

@RegisterDSL
class CreativeTab(private val  tabId: String){
    private var title: String = tabId
    private var icon: (() -> Item)? = null

    fun title(value: String){
        title = value
    }

    fun icon(itemSupplier: () -> Item){
        icon = itemSupplier
    }

    fun build(){
        val safeIcon = icon ?: { Items.STONE }
        ModCreativeTab.registerTab(tabId,title,safeIcon)
    }
}

fun creativeTab(id: String, init: CreativeTab.() -> Unit){
    val builder = CreativeTab(id)
    builder.init()
    builder.build()
}
