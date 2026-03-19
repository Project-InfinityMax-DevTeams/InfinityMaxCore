package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public interface EntityHurtLogic extends Logic {
    void execute(Entity entity, DamageSource source, float amount);
}
