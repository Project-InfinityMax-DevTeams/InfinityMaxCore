package com.yuyuto.infinitymaxcore.client;

import com.yuyuto.infinitymaxcore.entity.LogicEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GenericEntityRenderer extends EntityRenderer<LogicEntity> {

    private final ResourceLocation texture;

    public GenericEntityRenderer(EntityRendererProvider.Context context,ResourceLocation texture){
        super(context);
        this.texture = texture;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LogicEntity entity){
        return texture;
    }
}
