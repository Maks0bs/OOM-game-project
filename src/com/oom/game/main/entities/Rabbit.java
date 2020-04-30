package com.oom.game.main.entities;

/*
    Redundant separate class, should remove this and only use NPC class
 */

public class Rabbit extends NPC {
    public static final String NAME = "Rabbit";
    public static final int HEALTH_POINTS = 4, ATTACK_POINTS = 1, EXP_POINTS = 2;
    public Rabbit(){
        super(NAME, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        System.out.println("A new Rabbit has spawned!");
    }

    @Override
    public String getInfo() {
        return
            this.name + "\n" +
            "HP: " + this.getHealthPoints() + "\n" +
            "ATK: " + this.getAttackPoints() + "\n";
    }
}
