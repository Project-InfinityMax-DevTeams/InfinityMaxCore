package com.yuyuto.infinitymaxcore.entity;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.type.EntityAttackLogic;
import com.yuyuto.infinitymaxcore.logic.type.EntityHurtLogic;
import com.yuyuto.infinitymaxcore.logic.type.EntityInteractLogic;
import com.yuyuto.infinitymaxcore.logic.type.EntityTickLogic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class LogicEntity extends Entity {

    private final Map<LogicPhase, List<Logic>> logicMap;

    public LogicEntity(EntityType<?> type, Level level, Map<LogicPhase, List<Logic>> logicMap){
        super(type, level);
        this.logicMap = logicMap;
    }

    //よくわからない場所
    @Override
    protected void defineSynchedData(){
        //なにこれ珍百景すぎてわかんねぇ
    }
    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag){
        //同上
    }
    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag){
        //だからなんだよこれ
    }
    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket(){
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    //tick
    @Override
    public void tick(){
        super.tick();

        List<Logic> logics = logicMap.get(LogicPhase.ENTITY_TICK);
        if (logics != null){
            for (Logic logic : logics){
                if (logic instanceof EntityTickLogic tick){
                    tick.execute(this);
                }
            }
        }
    }

    //interact
    public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand hand){

        List<Logic> logics = logicMap.get(LogicPhase.ENTITY_INTERACT);
        if (logics != null){
            for (Logic logic : logics){
                if (logic instanceof EntityInteractLogic inter){
                    return inter.execute(player, this, hand);
                }
            }
        }
        return InteractionResult.PASS;
    }

    //hurt
    @Override
    public boolean hurt(@NotNull DamageSource source, float amount){
        List<Logic> logics = logicMap.get(LogicPhase.ENTITY_HURT);

        if (logics != null){
            for (Logic logic : logics){
                if (logic instanceof EntityHurtLogic hurt){
                    hurt.execute(this, source, amount);
                }
            }
        }

        return super.hurt(source, amount);
    }

    public void attackEntity(Entity target){
        List<Logic> logics = logicMap.get(LogicPhase.ENTITY_ATTACK);

        if (logics != null){
            for (Logic logic : logics){
                if (logic instanceof EntityAttackLogic attack){
                    attack.execute(this, target);
                }
            }
        }
    }
}