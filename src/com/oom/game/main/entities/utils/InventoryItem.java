package com.oom.game.main.entities.utils;

import java.io.Serializable;

/**
 * Class responsible ONLY for stats of items that can be found in the world. Each WorldItem ALWAYS has an InventoryItem
 */
public class InventoryItem implements Serializable {
    private String name = null;

    //FIXME this class should be expanded to provide normal items api

    public InventoryItem(String name){
        this.name = name;
    }
}
