package com.yuyuto.infinitymaxcore.client;

import com.yuyuto.infinitymaxcore.block.BlockEntityStorage;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BlockEntityClientRegistry {

    public static <T extends BlockEntity> void register(@NotNull BlockEntityStorage<T> storage){
        if (storage.getRenderer() == null) return;

        BlockEntityRenderers.register(
                storage.getBlockEntityType(),
                ctx -> storage.getRenderer()
        );
    }
}
