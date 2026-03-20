package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import com.yuyuto.infinitymaxcore.entity.LogicEntity;
import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicMapper;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class EntityFactory {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static @NotNull EntityType<?> create(@NotNull EntityValueStorage storage){

        Map<LogicPhase, List<Logic>> logicMap = LogicMapper.map(storage.getLogics());

        //Factory
        EntityType.EntityFactory<Entity> factory = (type,level) -> new LogicEntity(type, level, logicMap);
        //Builder
        EntityType.Builder<Entity> builder =
                EntityType.Builder.of(factory, storage.getCategory())
                        .sized(storage.getWidth(), storage.getHeight())
                        .setTrackingRange(storage.getTrackingRange())
                        .setUpdateInterval(storage.getUpdateInterval());

        if (storage.isFireImmune()) builder.fireImmune();
        if (!storage.isSummonable()) builder.noSummon();
        if (!storage.isSaveable()) builder.noSave();
        if (storage.isCanSpawnFarFromPlayer()) builder.canSpawnFarFromPlayer();
        if (!storage.isVelocityUpdates()) builder.setShouldReceiveVelocityUpdates(false);

        return builder.build(storage.getId());
    }
}
