package com.oom.game.main.entities.items;

import com.oom.game.main.entities.items.utils.InventoryContainer;

public class Backpack extends InventoryContainer {
    public static final int MAX_CAPACITY = 8;

    public Backpack(){
        super("Backpack");
    }

    @Override
    public String getName() {
        return "Backpack";
    }

    @Override
    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }
}
