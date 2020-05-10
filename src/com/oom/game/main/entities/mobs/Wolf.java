package com.oom.game.main.entities.mobs;

/*
    Redundant separate class, should remove this and only use NPC class
 */

import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.Position;

public class Wolf extends NPC {
    public static final String NAME = "Wolf";
    public static final int HEALTH_POINTS = 25, ATTACK_POINTS = 8, EXP_POINTS = 20;

    public Wolf(Position position){
        super(NAME, position, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        System.out.println("A new Rabbit has spawned!");
    }

    /*
        FIXME add a constructor to create a rabbit with custom stats
     */

    /**
     * {@link com.oom.game.main.entities.Entity}
     * @return basic info of wolf
     */
    @Override
    public String getInfo() {
        return
            this.name + "\n" +
            "HP: " + this.getHealthPoints() + "\n" +
            "ATK: " + this.getAttackPoints() + "\n";
    }
}
