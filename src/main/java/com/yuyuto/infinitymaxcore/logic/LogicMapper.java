package com.yuyuto.infinitymaxcore.logic;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LogicMapper {
    public static @NotNull Map<LogicPhase, List<Logic>> map(@NotNull Map<LogicPhase, List<String>> input){
        Map<LogicPhase, List<Logic>> logicMap = new EnumMap<>(LogicPhase.class);

        input.forEach((phase, ids) -> {
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

        return logicMap;
    }
}
