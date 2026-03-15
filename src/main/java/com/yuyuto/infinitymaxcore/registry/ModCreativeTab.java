package com.yuyuto.infinitymaxcore.registry;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModCreativeTab {

    private static final Map<String, List<Item>> TAB_ITEMS = new HashMap<>();

    public static void add(String tabId, Item item){
        TAB_ITEMS.computeIfAbsent(tabId, t -> new ArrayList<>()).add(item);
    }

    public static List<Item> get(String tabId){
        return TAB_ITEMS.getOrDefault(tabId, List.of());
    }
}
