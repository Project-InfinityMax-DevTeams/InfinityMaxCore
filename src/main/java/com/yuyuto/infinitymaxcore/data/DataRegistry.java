package com.yuyuto.infinitymaxcore.data;

import java.util.HashMap;
import java.util.Map;

public class DataRegistry {
    private static final Map<String, DataType<?>> TYPES = new HashMap<>();

    public static void register(String id, DataType<?> type){
        TYPES.put(id, type);
    }

    public static DataType<?> get(String id){
        return TYPES.get(id);
    }
}
