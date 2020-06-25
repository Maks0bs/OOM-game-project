package com.oom.game.main.entities.items.enchantments;

import com.oom.game.main.entities.items.utils.BuffItem;
import com.oom.game.main.entities.items.utils.BuffItemWrapper;

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

    @Override
    public String getName() {
        return null;
    }
}
