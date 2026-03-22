package com.yuyuto.infinitymaxcore.client;

import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import com.yuyuto.infinitymaxcore.entity.LogicEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = "infinitymaxcore", bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)

public class ModEntityRenderers {

    @SuppressWarnings({"unchecked"})
    private static <T extends LogicEntity> void register(EntityRenderersEvent.@NotNull RegisterRenderers event, EntityType<?> type, EntityRendererProvider<T> provider){
        event.registerEntityRenderer((EntityType<T>) type, provider);
    }
    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){
            if (storage.getRenderer() == null) continue;

            event.registerEntityRenderer(
                    (EntityType<? extends LogicEntity>) storage.getEntityType().get(),
                    (context) -> new GenericEntityRenderer(context, storage.getTexture())
            );
        }
    }
}