package com.oom.game.main.entities;

/*
    Redundant separate class, should remove this and only use NPC class
 */

public class Wolf extends NPC {
    public static final String NAME = "Wolf";
    public static final int HEALTH_POINTS = 25, ATTACK_POINTS = 8, EXP_POINTS = 20;
    public Wolf(){
        super(NAME, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        System.out.println("A new Wolf has spawned!");
    }

    @Override
    public String getInfo() {
        return
            this.name + "\n" +
            "HP: " + this.getHealthPoints() + "\n" +
            "ATK: " + this.getAttackPoints() + "\n";
    }
}
