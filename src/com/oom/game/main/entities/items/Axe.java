package com.oom.game.main.entities.items;

import com.oom.game.main.entities.utils.BuffItem;

public class Axe extends BuffItem {
    public static final int ATTACK_BASE = 10;

    public Axe(){
        super("Axe", 0, 1, ATTACK_BASE, 1);
    }
}
