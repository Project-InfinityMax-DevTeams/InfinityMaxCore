package com.yuyuto.infinitymaxcore.block;

import com.mojang.datafixers.types.Type;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

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
    
    public String getBlockEntityId(){
        return id;
    }

    public BlockEntityType.BlockEntitySupplier<?> getSupplier() {
        return supplier;
    }

    public void setSupplier(BlockEntityType.BlockEntitySupplier<?> supplier) {
        this.supplier = supplier;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Type<?> getDataType() {
        return dataType;
    }

    public void setDataType(Type<?> dataType) {
        this.dataType = dataType;
    }

    public BlockEntityTicker<?> getTicker() {
        return ticker;
    }

    public void setTicker(BlockEntityTicker<?> ticker) {
        this.ticker = ticker;
    }

    public BlockEntityRenderer<?> getRenderer() {
        return renderer;
    }

    public void setRenderer(BlockEntityRenderer<?> renderer) {
        this.renderer = renderer;
    }

    public MenuType<?> getMenu() {
        return menu;
    }

    public void setMenu(MenuType<?> menu) {
        this.menu = menu;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
