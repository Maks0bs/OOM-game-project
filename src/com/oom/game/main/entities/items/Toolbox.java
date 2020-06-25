package com.oom.game.main.entities.items;

import com.oom.game.main.entities.items.utils.BuffItem;
import com.oom.game.main.entities.items.utils.InventoryComponent;
import com.oom.game.main.entities.items.utils.InventoryContainer;

public class Toolbox extends InventoryContainer {
    public static final int MAX_CAPACITY = 4;

    public Toolbox(){
        super("Backpack");
    }

    @Override
    public void add(InventoryComponent component) {
        if (component instanceof BuffItem){
            super.add(component);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    @Override
    public String getName() {
        return "Toolbox";
    }
}
