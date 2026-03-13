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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlockRegister extends Block {

    //Storage参照
    private final BlockValueStorage storage;
    private Player player;

    public BlockRegister(Properties properties, BlockValueStorage storage){
        super(properties);
        this.storage = storage;
    }

    //Tickイベント
    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        runLogic(LogicPhase.TICK, level, pos, state);
    }

    //Useロジック
    public void use(Player player, Level level, BlockPos pos, BlockState state){
        this.player = player;
        runLogic(LogicPhase.USE,level,pos,state);
    }

    //RandomTickイベント
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        runLogic(LogicPhase.RANDOM_TICK, level,pos,state);
    }

    //Logic実行
    private void runLogic(LogicPhase phase, Level level, BlockPos pos, BlockState state){
        List<String> ids = storage.getLogics().get(phase);

        if(ids == null) return;
        for(String id : ids){
            Logic logic = LogicRegistry.get(id);
            if (logic instanceof TickLogic tick){
                tick.execute(level,pos,state);
            }
            if (logic instanceof UseLogic use){
                use.execute(player, level, pos, state);
            }
            if (logic instanceof RandomTickLogic random){
                random.execute((ServerLevel) level, pos, state, (RandomSource) random);
            }
        }

    }
}
