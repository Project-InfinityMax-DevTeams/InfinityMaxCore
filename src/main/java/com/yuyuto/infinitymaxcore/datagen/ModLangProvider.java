package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.block.BlockStorageRegistry;
import com.yuyuto.infinitymaxcore.block.BlockValueStorage;
import com.yuyuto.infinitymaxcore.entity.EntityStorageRegistry;
import com.yuyuto.infinitymaxcore.entity.EntityValueStorage;
import com.yuyuto.infinitymaxcore.item.ItemStorageRegistry;
import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {

    public ModLangProvider(PackOutput output){
        super(output, "infinitymaxcore", "en_us");
    }

    @Override
    protected void addTranslations(){
        for (BlockValueStorage storage : BlockStorageRegistry.getAll()){
            if (storage.getLang() != null){
                add("block.infinitymaxcore." + storage.getBlockId(), storage.getLang());
            }
        }
        for (ItemValueStorage storage : ItemStorageRegistry.getAll()){
            if (storage.getLang() != null){
                add("item.infinitymaxcore."+ storage.getItemId(), storage.getLang());
            }
        }
        for (EntityValueStorage storage : EntityStorageRegistry.getAll()){
            if (storage.getLang() != null){
                add("entity.infinitymaxore."+ storage.getId(), storage.getLang());
            }
        }
    }
}
