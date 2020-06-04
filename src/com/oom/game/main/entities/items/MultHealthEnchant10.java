package com.oom.game.main.entities.items;

import com.oom.game.main.entities.utils.BuffItem;
import com.oom.game.main.entities.utils.BuffItemWrapper;

public class MultHealthEnchant10 extends BuffItemWrapper {
    public MultHealthEnchant10(BuffItem item){
        super.buffItem = item;
    }

    @Override
    public int getAddHealth() {
        return super.buffItem.getAddHealth();
    }

    @Override
    public double getMultHealth() {
        return 1.1 * super.buffItem.getMultHealth();
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
