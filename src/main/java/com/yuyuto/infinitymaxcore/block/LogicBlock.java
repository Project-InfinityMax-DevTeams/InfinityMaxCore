package com.yuyuto.infinitymaxcore.block;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.type.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class LogicBlock extends Block {

    private final Map<LogicPhase, List<Logic>> logicMap;

    public LogicBlock(Properties properties, Map<LogicPhase, List<Logic>> logicMap){
        super(properties);
        this.logicMap = logicMap;
    }

    // ===== Tick =====
    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        runLogic(LogicPhase.BLOCK_TICK, level, pos, state, null, null, null, null);
    }

    // ===== RandomTick =====
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        runLogic(LogicPhase.BLOCK_RANDOM_TICK, level, pos, state, null, random, null, null);
    }

    // ===== Use =====
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                          @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit){

        List<Logic> logics = logicMap.get(LogicPhase.BLOCK_USE);
        if (logics != null) {
            for (Logic logic : logics) {
                if (logic instanceof BlockUseLogic use){
                    return use.execute(player, level, pos, state, hand);
                }
            }
        }
        return InteractionResult.PASS;
    }

    // ===== Neighbor Update =====
    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos fromPos, boolean moving){
        runLogic(LogicPhase.BLOCK_NEIGHBOR_UPDATE, level, pos, state, null, null, block, fromPos);
    }

    // ===== Entity Inside =====
    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity){
        runLogic(LogicPhase.BLOCK_ENTITY_INSIDE, level, pos, state, entity, null, null, null);
    }

    // ===== Step On =====
    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity){
        runLogic(LogicPhase.BLOCK_STEP_ON, level, pos, state, entity, null, null, null);
    }

    // ===== 共通実行 =====
    private void runLogic(LogicPhase phase, Level level, BlockPos pos, BlockState state,
                          Entity entity, RandomSource random, Block neighbor, BlockPos neighborPos){

        List<Logic> logics = logicMap.get(phase);
        if (logics == null) return;

        for (Logic logic : logics){

            if (phase == LogicPhase.BLOCK_TICK && logic instanceof BlockTickLogic tick){
                tick.execute(level,pos,state);
            }
            else if (phase == LogicPhase.BLOCK_RANDOM_TICK && logic instanceof BlockRandomTickLogic randomLogic){
                randomLogic.execute((ServerLevel) level, pos, state, random);
            }
            else if (phase == LogicPhase.BLOCK_NEIGHBOR_UPDATE && logic instanceof BlockNeighborUpdateLogic nb){
                nb.execute(level,pos,state,neighbor,neighborPos);
            }
            else if (phase == LogicPhase.BLOCK_ENTITY_INSIDE && logic instanceof BlockEntityInsideLogic inside){
                inside.execute(level,pos,state,entity);
            }
            else if (phase == LogicPhase.BLOCK_STEP_ON && logic instanceof BlockStepOnLogic step){
                step.execute(level,pos,state,entity);
            }
        }
    }
}