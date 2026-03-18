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

            RegistryObject<EntityType<?>> obj = ENTITIES.register(storage.getId(), () -> {
               EntityType.Builder<?> builder =
                       EntityType.Builder.of(storage.getFactory(), storage.getCategory())
                               .sized(storage.getWidth(), storage.getHeight())
                               .setTrackingRange(storage.getTrackingRange())
                               .setUpdateInterval(storage.getUpdateInterval());
               if (storage.isFireImmune()) builder.fireImmune();
               if (!storage.isSummonable()) builder.noSummon();
               if (!storage.isSaveable()) builder.noSave();
               if (storage.isCanSpawnFarFromPlayer()) builder.canSpawnFarFromPlayer();
               if (!storage.isVelocityUpdates()) builder.setShouldReceiveVelocityUpdates(false);

               return builder.build(storage.getId());
            });

            storage.setEntityType(obj);
        }
    }
}
