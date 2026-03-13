package com.yuyuto.infinitymaxcore.block;

import java.util.ArrayList;
import java.util.List;

public class BlockStorageRegistry {

    private static final List<BlockValueStorage> BLOCKS = new ArrayList<>();

    public static void register(BlockValueStorage storage){
        BLOCKS.add(storage);
    }
    public static List<BlockValueStorage> getAll(){
        return BLOCKS;
    }
}
