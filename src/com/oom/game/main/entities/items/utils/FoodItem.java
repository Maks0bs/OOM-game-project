package com.oom.game.main.entities.items.utils;

public abstract class FoodItem extends InventoryItem{
    public FoodItem(String name){
        super(name);
    }

    public abstract int getRestoredHunger();
}
