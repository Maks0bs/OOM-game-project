package com.oom.game.main.entities;

public class NPC extends Creature {
    public static final int DEFAULT_EXP_KILL_REWARD = 5;

    public NPC(String name, double x, double y, int healthPoints, int attackPoints){
        super(name, x, y, healthPoints, attackPoints);
        this.expPoints = DEFAULT_EXP_KILL_REWARD;
    }

    public NPC(String name, int healthPoints, int attackPoints){
        super(name, healthPoints, attackPoints);
        this.expPoints = DEFAULT_EXP_KILL_REWARD;
    }

    public NPC(String name, int healthPoints, int attackPoints, int expPoints) {
        super(name, healthPoints, attackPoints, expPoints);
    }



    public void counterAttack(Creature attacker){
        attacker.addHealthPoints(-this.getAttackPoints());
    }

    public void onDeathAction(){
        System.out.println(this.name + " was defeated and drops " + this.expPoints + " XP points");
    }
}
