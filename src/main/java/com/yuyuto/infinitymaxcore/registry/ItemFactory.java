package com.yuyuto.infinitymaxcore.registry;

import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import net.minecraft.world.item.Item;

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
       return new Item(props);
    }
}
