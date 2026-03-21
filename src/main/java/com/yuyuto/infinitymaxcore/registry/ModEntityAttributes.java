package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "infinitymaxcore", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){
            if(storage.getAttribute() == null) continue;
            AttributeSupplier.Builder builder = Mob.createMobAttributes();
            storage.getAttribute().forEach(builder::add);
            event.put((EntityType<? extends LivingEntity>) storage.getEntityType().get(),builder.build());
        }
    }

    //TODO;ModEntitySpawns実装
}
