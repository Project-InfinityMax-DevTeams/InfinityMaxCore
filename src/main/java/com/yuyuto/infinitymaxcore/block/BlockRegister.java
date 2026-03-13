package com.yuyuto.infinitymaxcore.block;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.LogicRegistry;
import com.yuyuto.infinitymaxcore.logic.type.RandomTickLogic;
import com.yuyuto.infinitymaxcore.logic.type.TickLogic;
import com.yuyuto.infinitymaxcore.logic.type.UseLogic;
import com.yuyuto.infinitymaxcore.registry.BlockValueStorage;
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

public class BlockRegister extends Block {

    //Storage参照
    private final BlockValueStorage storage;

    public BlockRegister(Properties properties, BlockValueStorage storage){
        super(properties);
        this.storage = storage;
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
        runLogic(LogicPhase.TICK,level,pos,state,null,null);
    }
    private void runLogicUse(Level level, BlockPos pos, BlockState state, Player player){
        runLogic(LogicPhase.USE,level,pos,state,player,null);
    }
    private void runLogicRandomTick(Level level, BlockPos pos, BlockState state, RandomSource random){
        runLogic(LogicPhase.RANDOM_TICK,level,pos,state,null,random);
    }

    //Logic実行
    private void runLogic(LogicPhase phase, Level level, BlockPos pos, BlockState state, Player player, RandomSource random){
        List<String> ids = storage.getLogics().get(phase);

        if(ids == null) return;
        for(String id : ids){
            Logic logic = LogicRegistry.get(id);
            if (phase == LogicPhase.TICK && logic instanceof TickLogic tick){
                tick.execute(level,pos,state);
            }
            if (phase == LogicPhase.USE && logic instanceof UseLogic use){
                use.execute(player, level, pos, state);
            }
            if (phase == LogicPhase.RANDOM_TICK && logic instanceof RandomTickLogic randomLogic){
                randomLogic.execute((ServerLevel) level, pos, state, random);
            }
        }

    }
}
