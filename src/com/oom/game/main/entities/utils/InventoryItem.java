package com.oom.game.main.entities.utils;

/**
 * Class responsible ONLY for stats of items that can be found in the world. Each WorldItem ALWAYS has an InventoryItem
 */
public class InventoryItem {
    private String name = null;

    public InventoryItem(String name){
        this.name = name;
    }
}
