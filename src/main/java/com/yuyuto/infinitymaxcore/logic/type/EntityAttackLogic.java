package com.yuyuto.infinitymaxcore.logic.type;

import com.yuyuto.infinitymaxcore.logic.Logic;
import net.minecraft.world.entity.Entity;

public interface EntityAttackLogic extends Logic {
    void execute(Entity attacker, Entity target);
}
