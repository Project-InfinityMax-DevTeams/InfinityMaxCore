package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "infinitymaxcore");
    public static final Map<String, RegistryObject<EntityType<?>>> ENTITY_MAP = new LinkedHashMap<>();
    public static void register(){
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static RegistryObject<EntityType<?>> registerEntity(@NotNull EntityValueStorage storage){
        RegistryObject<EntityType<?>> object = ENTITIES.register(
                storage.getId(), ()
                -> EntityFactory.create(storage)
        );

        storage.setEntityType(object);
        ENTITY_MAP.put(storage.getId(),object);

        return object;
    }
}
