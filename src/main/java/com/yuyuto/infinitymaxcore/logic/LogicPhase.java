package com.yuyuto.infinitymaxcore.logic;

public enum LogicPhase {

    // BLOCK
    BLOCK_TICK,
    BLOCK_RANDOM_TICK,
    BLOCK_USE,
    BLOCK_NEIGHBOR_UPDATE,
    BLOCK_ENTITY_INSIDE,

    // ITEM
    ITEM_USE,
    ITEM_TICK,
    ITEM_INVENTORY_TICK,
    ITEM_RELEASE_USE,

    //ENTITY
    ENTITY_INTERACT,
    ENTITY_TICK,
    ENTITY_HURT,
    ENTITY_ATTACK,

    //OTHER
    STEP_ON,
    ANIMATE
}
