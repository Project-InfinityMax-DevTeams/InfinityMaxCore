package com.yuyuto.infinitymaxcore.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityStorageRegistry {

    private static final List<EntityValueStorage> ENTITIES = new ArrayList<>();

    public static void register(EntityValueStorage storage){
        ENTITIES.add(storage);
    }

    public static  List<EntityValueStorage> getAll(){
        return ENTITIES;
    }
}
