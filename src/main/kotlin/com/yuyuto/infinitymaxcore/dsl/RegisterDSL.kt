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
import com.yuyuto.infinitymaxcore.item.ItemValueStorage
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

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

    fun block(id: String, init: BlockDSLBuilder.() -> Unit): BlockValueStorage{
        val storage = BlockValueStorage(id)
        val builder = BlockDSLBuilder(storage)

        builder.init()
        BlockStorageRegistry.register(storage)
        return storage
    }
}

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

    //Recipeは入れるものの、Defを展開するClass記述のレベルが跳ね上がってて自決できないため、くやしいけれどわかるまで省略

    fun parentModel(value: String){
        storage.parentModel = value
    }

    //Logicはまだ未定なのでここでは割愛。
    //ここでいうLogicはアイテムに埋め込むゲーム処理のことを言う。
    //例:銃火器系、魔法弾発射の杖、マシンリペアツールなど

    fun item(id: String, init: ItemDSLBuilder.() -> Unit): ItemValueStorage{
        val storage = ItemValueStorage(id)
        val scope = ItemDSLBuilder(storage)
        scope.init()

        return storage
    }
}

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