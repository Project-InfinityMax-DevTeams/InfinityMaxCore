package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import com.yuyuto.infinitymaxcore.item.LogicItem;
import com.yuyuto.infinitymaxcore.logic.Logic;
import com.yuyuto.infinitymaxcore.logic.LogicMapper;
import com.yuyuto.infinitymaxcore.logic.LogicPhase;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ItemFactory {
    public static @NotNull Item create(@NotNull ItemValueStorage storage){
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
        Map<LogicPhase, List<Logic>> logicMap = LogicMapper.map(storage.getLogics());
        Item item = new LogicItem(props, logicMap);

        if (storage.getCreativeTabId() != null){
            CreativeTabFactory.add(storage.getCreativeTabId(), item);
        }

        return item;
    }
}
