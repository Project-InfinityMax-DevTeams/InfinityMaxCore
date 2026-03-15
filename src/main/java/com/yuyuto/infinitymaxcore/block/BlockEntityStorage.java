package com.yuyuto.infinitymaxcore.block;

import com.mojang.datafixers.types.Type;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;

public class BlockEntityStorage {

    //BlockEntity登録ID
    private final String id;

    //生成状態
    private BlockEntityType.BlockEntitySupplier<?> supplier;
    //対応ブロック
    private List<Block> blocks;
    //データタイプ
    private Type<?> dataType;
    //Tick処理
    private BlockEntityTicker<?> ticker;
    //レンダリング系(ブロックアニメーションなど。)
    private BlockEntityRenderer<?> renderer;
    //GUI
    private MenuType<?> menu;
    //ネット同期
    private boolean sync = false;

    //DataGen関連は以下に記述。

    public BlockEntityStorage(String id){
        this.id = id;
    }
    
    public void getBlockEntityId(){
        return id
    }
}
