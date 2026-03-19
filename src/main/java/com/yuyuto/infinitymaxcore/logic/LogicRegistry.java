package com.yuyuto.infinitymaxcore.logic;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LogicRegistry {
    private static final Map<String,Logic> LOGIC = new HashMap<>();

    public static void register(@NotNull Logic logic){
        if (LOGIC.containsKey(logic.id())){
            System.err.println("[InfinityMax] Duplication Logic ID: " + logic.id());
        }
        LOGIC.put(logic.id(), logic);
    }

    public static Logic get(String id){
        return LOGIC.get(id);
    }
}
