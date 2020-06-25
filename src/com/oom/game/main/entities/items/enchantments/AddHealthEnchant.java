package com.oom.game.main.entities.items.enchantments;

import com.oom.game.main.entities.items.utils.BuffItem;
import com.oom.game.main.entities.items.utils.BuffItemWrapper;

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


    @Override
    public String getName() {
        return null;
    }
}
