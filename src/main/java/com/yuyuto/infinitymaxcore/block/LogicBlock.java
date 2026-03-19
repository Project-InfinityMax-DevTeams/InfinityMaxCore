package com.yuyuto.infinitymaxcore.block;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.type.BlockRandomTickLogic;
import com.yuyuto.infinitymaxcore.logic.type.BlockTickLogic;
import com.yuyuto.infinitymaxcore.logic.type.BlockUseLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LogicBlock extends Block {

    //Storage参照
    private final Map<LogicPhase, List<Logic>> logicMap;

    public LogicBlock(Properties properties, Map<LogicPhase, List<Logic>> logicMap){
        super(properties);
        this.logicMap = logicMap;
    }

    //Tickイベント
    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        runLogicTick(level, pos, state);
    }

    //Useロジック
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit){
        runLogicUse(level,pos,state,player);
        return InteractionResult.SUCCESS;
    }

    //RandomTickイベント
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        runLogicRandomTick(level,pos,state,random);
    }
    //API関数(引数干渉の削減に)
    private void runLogicTick(Level level, BlockPos pos, BlockState state){
        runLogic(LogicPhase.BLOCK_TICK,level,pos,state,null,null);
    }
    private void runLogicUse(Level level, BlockPos pos, BlockState state, Player player){
        runLogic(LogicPhase.BLOCK_USE,level,pos,state,player,null);
    }
    private void runLogicRandomTick(Level level, BlockPos pos, BlockState state, RandomSource random){
        runLogic(LogicPhase.BLOCK_RANDOM_TICK,level,pos,state,null,random);
    }

    //Logic実行
    private void runLogic(LogicPhase phase, Level level, BlockPos pos, BlockState state, Player player, RandomSource random){
        List<Logic> logics = logicMap.get(phase);

        if(logics == null) {
            System.err.println("[InfinityMax ErrorCode:X0000f1] No logic was found: " + logics + ". This will be enable when you install the dedicatedMOD. Alternatively, Please implement a Logic class corresponding to the ID. Please send the error code to the support Discord Server or to GitHub.");
        }

        for(Logic logic : Objects.requireNonNull(logics)){

            if (phase == LogicPhase.BLOCK_TICK && logic instanceof BlockTickLogic tick){
                tick.execute(level,pos,state);
            } else if (phase == LogicPhase.BLOCK_USE && logic instanceof BlockUseLogic use){
                use.execute(player, level, pos, state);
            } else if (phase == LogicPhase.BLOCK_RANDOM_TICK && logic instanceof BlockRandomTickLogic randomLogic){
                randomLogic.execute((ServerLevel) level, pos, state, random);
            }
        }

    }
}