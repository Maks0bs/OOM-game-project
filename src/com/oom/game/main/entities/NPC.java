package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

public class NPC extends Creature {
    /*
        In this case this.expPoints is the reward
        that players receive after defeating the NPC
     */
    public static final int DEFAULT_EXP_KILL_REWARD = 5;

    public NPC(String name, Position position, int healthPoints, int attackPoints, int expPoints) {
        super(name, position, healthPoints, attackPoints, expPoints);
    }



    public void counterAttack(Creature attacker){
        attacker.addHealthPoints(-this.getAttackPoints());
    }

    public void onDeathAction(){
        System.out.println(this.name + " was defeated and drops " + this.expPoints + " XP points");
    }
}
