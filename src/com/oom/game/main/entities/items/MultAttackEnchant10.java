package com.oom.game.main.entities.items;

import com.oom.game.main.entities.utils.BuffItem;
import com.oom.game.main.entities.utils.BuffItemWrapper;

/**
 * Enchants weapon to add 10% of current attack power to wearer
 */
public class MultAttackEnchant10 extends BuffItemWrapper {
    public MultAttackEnchant10(BuffItem item){
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
        return super.buffItem.getAddAttack();
    }

    @Override
    public double getMultAttack() {
        return 1.1 * super.buffItem.getMultAttack();
    }
}
