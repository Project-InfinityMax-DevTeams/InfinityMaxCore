package com.yuyuto.infinitymaxcore.item;

import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.type.ItemReleaseUseLogic;
import com.yuyuto.infinitymaxcore.logic.type.ItemTickLogic;
import com.yuyuto.infinitymaxcore.logic.type.ItemUseLogic;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicItem extends Item {

    private final Map<LogicPhase, List<Logic>> logicMap = new HashMap<>();

    public LogicItem(Properties props, Map<LogicPhase, List<Logic>> logicMap){
        super(props);
        this.logicMap.putAll(logicMap);
    }

    // ===== Use =====
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand){

        List<Logic> logics = logicMap.get(LogicPhase.ITEM_USE);
        if (logics != null) {
            for (Logic logic : logics) {
                if (logic instanceof ItemUseLogic useLogic) {
                    return useLogic.execute(player, level, player.getItemInHand(hand), hand);
                }
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    // ===== Inventory Tick =====
    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean selected){

        List<Logic> logics = logicMap.get(LogicPhase.ITEM_TICK);
        if (logics != null){
            for (Logic logic : logics){
                if (logic instanceof ItemTickLogic tick){
                    tick.execute(level, entity, stack, slot, selected);
                }
            }
        }
    }

    // ===== Release Use =====
    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int timeLeft){

        List<Logic> logics = logicMap.get(LogicPhase.ITEM_RELEASE_USE);
        if (logics != null){
            for (Logic logic : logics){
                if (logic instanceof ItemReleaseUseLogic rel){
                    rel.execute(level, (Player) entity, stack, timeLeft);
                }
            }
        }
    }
}