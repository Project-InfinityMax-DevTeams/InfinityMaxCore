package com.yuyuto.infinitymaxcore.logic;

import java.util.HashMap;
import java.util.Map;

public class LogicRegistry {
    private static final Map<String,Logic> LOGIC = new HashMap<>();

    public static void register(Logic logic){
        LOGIC.put(logic.id(), logic);
    }

    public static Logic get(String id){
        return LOGIC.get(id);
    }
}
