package com.yuyuto.infinitymaxcore.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BaseBlockEntity<T extends BlockEntity> extends BlockEntity {

    private final BlockEntityStorage<T> storage;

    public BaseBlockEntity(BlockPos pos, BlockState state, @NotNull BlockEntityStorage<T> storage){
        super(storage.getBlockEntityType(), pos, state);
        this.storage = storage;
    }

    // Sync
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return storage.isSync() ? ClientboundBlockEntityDataPacket.create(this) : null;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return storage.isSync() ? this.saveWithoutMetadata() : super.getUpdateTag();
    }
}