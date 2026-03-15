package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import com.yuyuto.infinitymaxcore.item.LogicItem;
import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import com.yuyuto.infinitymaxcore.logic.LogicRegistry;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ItemFactory {
    public static Item create(ItemValueStorage storage){
        Item.Properties props = new Item.Properties()
                .rarity(storage.getRarity())
                .stacksTo(storage.getMaxStack())
                .craftRemainder(storage.getCraftingRemainingItem());
        if (storage.getFood() != null){
            props.food(storage.getFood().toMinecraftFood());
        }
        if (storage.isFireResistance()){
            props.fireResistant();
        }
        if(storage.getMaxDamage() > 0 && storage.getMaxStack() == 0){
            props.durability(storage.getMaxDamage());
        }

        //logic変換
        Map<LogicPhase, List<Logic>> logicMap = new EnumMap<>(LogicPhase.class);
        storage.getLogics().forEach((phase, ids) -> {
            List<Logic> list = new ArrayList<>();

            for (String id : ids){
                list.add(LogicRegistry.get(id));
            }

            logicMap.put(phase, list);
        });
        Item item = new LogicItem(props, logicMap);

        if (storage.getCreativeTabId() != null){
            ModCreativeTab.add(storage.getCreativeTabId(), item);
        }

        return item;
    }
}
