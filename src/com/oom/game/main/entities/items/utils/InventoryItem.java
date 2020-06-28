package com.oom.game.main.entities.items.utils;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Class responsible ONLY for stats of items that can be found in the world. Each WorldItem ALWAYS has an InventoryItem
 */
public abstract class InventoryItem implements InventoryComponent, Serializable {
    private String name = null;

    //FIXME this class should be expanded to provide normal items api

    public InventoryItem(String name){
        this.name = name;
    }


    @Override
    public void add(InventoryComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(InventoryComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InventoryComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<InventoryComponent> iterator() {
        return new Iterator<InventoryComponent>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public InventoryComponent next() {
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
