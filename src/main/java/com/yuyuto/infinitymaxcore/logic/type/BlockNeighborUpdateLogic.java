package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface BlockNeighborUpdateLogic extends Logic {
    void execute(Level level, BlockPos pos, BlockState state, Block neighborBlock, BlockPos neighborPos);
}
