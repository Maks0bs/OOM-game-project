package com.oom.game.main.entities.items;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.items.utils.InventoryItem;

public class Stick extends InventoryItem {
    public static final int THROW_DAMAGE = 5;

    public Stick(){
        super("Stick");
    }

    @Override
    public String getName() {
        return "Stick";
    }

    public void toss(Creature victim){
        // just for demonstration
        // TODO implement throwable items
    }
}
