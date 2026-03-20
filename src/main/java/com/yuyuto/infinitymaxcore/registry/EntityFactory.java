package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import com.yuyuto.infinitymaxcore.entity.LogicEntity;
import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.LogicRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EntityFactory {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static @NotNull EntityType<?> create(@NotNull EntityValueStorage storage){

        Map<LogicPhase, List<Logic>> logicMap = new EnumMap<>(LogicPhase.class);

        storage.getLogics().forEach((phase,ids) -> {
            List<Logic> list = new ArrayList<>();

            for (String id : ids){
                Logic logic = LogicRegistry.get(id);

                if (logic != null){
                    list.add(logic);
                } else {
                    System.err.println("[InfinityMax] Logic not found: " + id);
                }
            }
            logicMap.put(phase,list);
        });

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
