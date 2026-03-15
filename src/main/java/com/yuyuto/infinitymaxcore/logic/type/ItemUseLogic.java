package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public interface ItemUseLogic extends Logic {
     @NotNull InteractionResultHolder<ItemStack> execute(
            Player player,
            Level level,
            ItemStack stack,
            InteractionHand hand
    );
}
