package com.yuyuto.infinitymaxcore.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,"infinitymaxcore");

    public static <T extends BlockEntity> void register(@NotNull BlockEntityStorage<T> storage){
        RegistryObject<BlockEntityType<T>> obj =
                BLOCK_ENTITIES.register(storage.getBlockEntityId(), () ->
                        BlockEntityType.Builder.of(
                                storage.getSupplier(),
                                 storage.getBlocks().toArray(new Block[0])
                        ).build(storage.getDataType())
                );
        storage.setBlockEntityType(obj.get());
    }
}
