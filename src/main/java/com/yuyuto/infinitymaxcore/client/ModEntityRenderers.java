package com.yuyuto.infinitymaxcore.client;
import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = "infinitymaxcore", bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)

public class ModEntityRenderers {

    @SuppressWarnings({"unchecked","rawtypes"})
    private static void register(EntityRenderersEvent.RegisterRenderers event, EntityType<?> type, EntityRendererProvider<?> provider){
        event.registerEntityRenderer((EntityType) type, (EntityRendererProvider) provider);
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){
            if (storage.getRenderer() != null && storage.getEntityType() != null){
                register(event,storage.getEntityType().get(), storage.getRenderer());
            }
        }
    }
}