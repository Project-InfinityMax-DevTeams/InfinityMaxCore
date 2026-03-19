package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ItemReleaseUseLogic extends Logic {
    void execute(Level level, Player player, ItemStack stack, int timeLeft);
}
