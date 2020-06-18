package com.oom.game.main.entities.items;

import com.oom.game.main.entities.utils.BuffItem;
import com.oom.game.main.entities.utils.BuffItemWrapper;

/**
 * Enchants weapon to add 1 HP to wearer
 */
public class AddHealthEnchant extends BuffItemWrapper {
    public AddHealthEnchant(BuffItem item){
        super.buffItem = item;
    }

    @Override
    public int getAddHealth() {
        return 1 + super.buffItem.getAddHealth();
    }

    @Override
    public double getMultHealth() {
        return super.buffItem.getMultHealth();
    }

    @Override
    public int getAddAttack() {
        return super.buffItem.getAddAttack();
    }

    @Override
    public double getMultAttack() {
        return super.buffItem.getMultAttack();
    }
}
