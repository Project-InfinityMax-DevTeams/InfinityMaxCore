package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "infinitymaxcore", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void onRegisterSpawns(SpawnPlacementRegisterEvent event){
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){
            EntityType<?> type = storage.getEntityType().get();

            event.register(
                    type,
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (entityType,level,reason,pos,random) -> true,
                    SpawnPlacementRegisterEvent.Operation.REPLACE
            );
        }
    }
}
