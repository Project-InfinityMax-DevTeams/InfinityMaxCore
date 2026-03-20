package com.yuyuto.infinitymaxcore.registry;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreativeTabFactory {

    private static final Map<String, List<Item>> TAB_ITEMS = new HashMap<>();

    // アイテム追加（ItemFactoryから呼ばれる）
    public static void add(String tabId, Item item){
        TAB_ITEMS.computeIfAbsent(tabId, t -> new ArrayList<>()).add(item);
    }

    // タブ表示用取得
    public static List<Item> get(String tabId){
        return TAB_ITEMS.getOrDefault(tabId, List.of());
    }
}
