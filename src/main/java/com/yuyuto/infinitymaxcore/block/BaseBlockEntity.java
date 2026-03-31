package com.yuyuto.infinitymaxcore.block;

import com.mojang.datafixers.types.Type;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BaseBlockEntity<T extends BlockEntity> extends BlockEntity {

    private final BlockEntityStorage<T> storage;
    private final Map<Type<?>, Object> dataMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <D> D getData(Type<D> type){
        return (D) dataMap.computeIfAbsent(type, t -> {
            try {
                return type.codec().parse(NbtOps.INSTANCE, new CompoundTag())
                        .result()
                        .orElse(null);
            } catch (Exception e) {
                return null;
            }
        });
    }

    public <D> void setData(Type<D> type, D data){
        dataMap.put(type, data);
    }

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