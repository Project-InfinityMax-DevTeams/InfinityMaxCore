package com.yuyuto.infinitymaxcore.dsl

import com.yuyuto.infinitymaxcore.logic.LogicPhase
import com.yuyuto.infinitymaxcore.registry.BlockValueStorage
import net.minecraft.resources.ResourceLocation
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
        return storage
    }
}