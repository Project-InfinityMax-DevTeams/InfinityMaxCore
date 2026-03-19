package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static  final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "infinitymaxcore");

    public static void registerAll(){
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){

            RegistryObject<EntityType<?>> obj = ENTITIES.register(
                    storage.getId(),() -> EntityFactory.create(storage)
            );

            storage.setEntityType(obj);
        }
    }
}
