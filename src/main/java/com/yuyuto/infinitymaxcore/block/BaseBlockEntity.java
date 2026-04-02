package com.yuyuto.infinitymaxcore.block;

import com.yuyuto.infinitymaxcore.data.DataRegistry;
import com.yuyuto.infinitymaxcore.data.DataType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BaseBlockEntity<T extends BlockEntity> extends BlockEntity {

    private final BlockEntityStorage<T> storage;
    private final Map<DataType<?>, Object> dataMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <D> D getData(DataType<D> type) {
        return (D) dataMap.computeIfAbsent(type, t -> type.create());
    }

    public <D> void setData(DataType<D> type, D data){
        dataMap.put(type, data);
    }

    public BaseBlockEntity(BlockPos pos, BlockState state, @NotNull BlockEntityStorage<T> storage){
        super(storage.getBlockEntityType(), pos, state);
        this.storage = storage;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        CompoundTag dataTag = new CompoundTag();

        for (Map.Entry<DataType<?>, Object> entry : dataMap.entrySet()) {

            DataType<?> type = entry.getKey();
            Object value = entry.getValue();

            encode(type, value).ifPresent(nbt -> dataTag.put(type.id(), nbt));
        }

        tag.put("data", dataTag);
    }

    @SuppressWarnings("unchecked")
    private <T> Optional<Tag> encode(DataType<T> type, Object value) {
        return type.codec()
                .encodeStart(NbtOps.INSTANCE, (T) value)
                .result();
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);

        if (!tag.contains("data")) return;

        CompoundTag dataTag = tag.getCompound("data");

        for (String key : dataTag.getAllKeys()) {

            DataType<?> type = DataRegistry.get(key);
            if (type == null) continue;

            decode(type, dataTag.get(key)).ifPresent(value -> dataMap.put(type, value));
        }
    }

    private <T> Optional<T> decode(DataType<T> type, Tag tag) {
        return type.codec()
                .parse(NbtOps.INSTANCE, tag)
                .result();
    }

    public Collection<Object> getAllData(){
        return dataMap.values();
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