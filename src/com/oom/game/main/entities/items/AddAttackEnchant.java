package com.oom.game.main.entities.items;

import com.oom.game.main.entities.utils.BuffItem;
import com.oom.game.main.entities.utils.BuffItemWrapper;

/**
 * Adds 1 attack point to the weapon
 */
public class AddAttackEnchant extends BuffItemWrapper {
    public AddAttackEnchant(BuffItem item){
        super.buffItem = item;
    }

    @Override
    public int getAddHealth() {
        return super.buffItem.getAddHealth();
    }

    @Override
    public double getMultHealth() {
        return super.buffItem.getMultHealth();
    }

    @Override
    public int getAddAttack() {
        return 1 + super.buffItem.getAddAttack();
    }

    @Override
    public double getMultAttack() {
        return super.buffItem.getMultAttack();
    }
}
