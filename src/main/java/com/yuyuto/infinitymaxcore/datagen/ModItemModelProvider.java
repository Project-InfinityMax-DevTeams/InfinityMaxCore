package com.yuyuto.infinitymaxcore.datagen;

import com.yuyuto.infinitymaxcore.item.ItemStorageRegistry;
import com.yuyuto.infinitymaxcore.item.ItemValueStorage;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper helper){
        super(output, "infinitymaxcore", helper);
    }

    @Override
    protected void registerModels(){
        for(ItemValueStorage storage : ItemStorageRegistry.getAll()){
            String id = storage.getItemId();
            if (storage.getModel() == null){
                withExistingParent(id,mcLoc(storage.getParentModel())).texture("layer0",modLoc("item/" + id));
            }
        }
    }
}
