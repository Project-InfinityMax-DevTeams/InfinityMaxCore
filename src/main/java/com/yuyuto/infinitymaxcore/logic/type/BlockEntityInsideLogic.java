package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface BlockEntityInsideLogic extends Logic {
    void execute(Level level, BlockPos pos, BlockState state, Entity entity);
}
