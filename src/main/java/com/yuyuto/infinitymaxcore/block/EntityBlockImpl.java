package com.yuyuto.infinitymaxcore.block;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class EntityBlockImpl<T extends BlockEntity> extends LogicBlock implements EntityBlock {

    // BlockValueStorage（Block側の情報）
    private final BlockValueStorage blockStorage;

    // BlockEntityStorage（BE側の情報）
    private final BlockEntityStorage<T> beStorage;

    @SuppressWarnings("unchecked")
    public EntityBlockImpl(Properties props,
                           Map<LogicPhase, List<Logic>> logicMap,
                           BlockValueStorage blockStorage) {

        super(props, logicMap);
        this.blockStorage = blockStorage;

        // BlockEntityStorage取得
        this.beStorage = (BlockEntityStorage<T>) blockStorage.getBlockEntity();
    }

    // ===== BlockEntity生成 =====
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        if (beStorage == null || beStorage.getSupplier() == null) return null;

        // supplierから生成（Storageを使う）
        return beStorage.getSupplier().create(pos, state);
    }

    // ===== Ticker接続 =====
    @SuppressWarnings("unchecked")
    @Override
    public <E extends BlockEntity> BlockEntityTicker<E> getTicker(@NotNull Level level,
                                                                  @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<E> type) {

        if (beStorage == null || beStorage.getTicker() == null) return null;

        // 型一致チェック（安全性確保）
        if (beStorage.getBlockEntityType() != type) return null;

        // Forge仕様的にキャストは必須
        return (lvl, pos, st, be) -> beStorage.getTicker().tick(lvl, pos, st, (T) be);
    }

    // ===== Menu（GUI） =====
    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {

        if (beStorage == null || beStorage.getMenu() == null) return null;

        return new SimpleMenuProvider(
                (id, inv, player) -> {
                    // MenuTypeからMenu生成
                    return beStorage.getMenu().create(id, inv);
                },
                Component.literal(blockStorage.getBlockId())
        );
    }
}