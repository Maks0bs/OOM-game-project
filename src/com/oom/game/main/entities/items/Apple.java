package com.oom.game.main.entities.items;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.items.utils.FoodItem;
import com.oom.game.main.entities.items.utils.InventoryItem;

public class Apple extends FoodItem {
    public static final int THROW_DAMAGE = 5;

    public Apple(){
        super("Apple");
    }

    @Override
    public String getName() {
        return "Apple";
    }

    @Override
    public int getRestoredHunger() {
        return 5;
    }
}
