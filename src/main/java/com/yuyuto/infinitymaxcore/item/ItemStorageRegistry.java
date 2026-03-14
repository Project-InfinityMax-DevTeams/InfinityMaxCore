package com.yuyuto.infinitymaxcore.item;

import java.util.ArrayList;
import java.util.List;

public class ItemStorageRegistry {

    public static final List<ItemValueStorage> ITEMS = new ArrayList<>();

    public static void register(ItemValueStorage storage){
        ITEMS.add(storage);
    }
    public static List<ItemValueStorage> getAll(){
        return ITEMS;
    }
}
