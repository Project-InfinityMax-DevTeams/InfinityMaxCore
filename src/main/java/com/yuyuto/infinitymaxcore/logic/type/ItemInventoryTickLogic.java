package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ItemInventoryTickLogic extends Logic {
    void execute(Level level, Entity entity, ItemStack stack, int slot, boolean selected);
}
