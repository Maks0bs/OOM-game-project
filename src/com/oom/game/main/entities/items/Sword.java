package com.oom.game.main.entities.items;

import com.oom.game.main.entities.items.utils.BuffItem;


public class Sword extends BuffItem {
    public static final int ATTACK_BASE = 15;

    public Sword(){
        super("Sword", 0, 1, ATTACK_BASE, 1);
    }

    @Override
    public String getName() {
        return "Sword";
    }
}
