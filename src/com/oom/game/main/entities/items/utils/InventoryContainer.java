package com.oom.game.main.entities.items.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class InventoryContainer implements InventoryComponent, Serializable {
    private String name = null;
    private ArrayList<InventoryComponent> components = new ArrayList<>();

    //FIXME this class should be expanded to provide normal items api

    public InventoryContainer(String name){
        this.name = name;
    }

    @Override
    public void add(InventoryComponent component) {
        if (components.size() < getMaxCapacity()){
            components.add(component);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void remove(InventoryComponent component) {
        if (components.size() > 0 && components.contains(component)){
            components.remove(component);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public InventoryComponent getChild(int i) {
        if (i < components.size()){
            return components.get(i);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<InventoryComponent> iterator() {
        return components.iterator();
    }

    /**
     *
     * @return the amount of other components in this one
     */
    public int getAmount(){
        return components.size();
    }

    /**
     *
     * @return the max amount of components that fit in this container
     */
    public abstract int getMaxCapacity();
}
