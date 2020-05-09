package com.oom.game.main.entities.mobs;

/*
    Redundant separate class, should remove this and only use NPC class
 */

import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.utils.Position;

public class Rabbit extends NPC {
    public static final String NAME = "Rabbit";
    public static final int HEALTH_POINTS = 4, ATTACK_POINTS = 1, EXP_POINTS = 2;

    public Rabbit(Position position){
        super(NAME, position, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        System.out.println("A new Rabbit has spawned!");
    }
    /*
        FIXME add a constructor to create a rabbit with custom stats
     */

    /**
     * {@link com.oom.game.main.entities.Entity}
     * @return basic info of rabbit
     */
    @Override
    public String getInfo() {
        return
            this.name + "\n" +
            "HP: " + this.getHealthPoints() + "\n" +
            "ATK: " + this.getAttackPoints() + "\n";
    }
}
