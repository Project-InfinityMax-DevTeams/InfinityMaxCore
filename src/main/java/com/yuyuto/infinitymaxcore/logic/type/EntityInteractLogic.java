package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface EntityInteractLogic extends Logic {
    InteractionResult execute(Player player, Entity entity, InteractionHand hand);
}
