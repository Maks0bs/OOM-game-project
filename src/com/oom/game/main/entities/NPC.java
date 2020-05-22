package com.oom.game.main.entities;

import com.oom.game.main.entities.interaction.AggresiveBehaviour;
import com.oom.game.main.environment.Position;

public class NPC extends Creature {
    /*
        In this case this.expPoints is the reward
        that players receive after defeating the NPC
     */
    /*
        FIXME expand UML with strategies
     */
    //FIXME refactor creatures and entities with strategy pattern

    public static final int DEFAULT_EXP_KILL_REWARD = 5;

    /*
     *  FIXME when strategies in this class change, a special method should be called
     *  FIXME to notify the creature (the same for block) to change its texture
     */
    protected AggresiveBehaviour aggresiveBehaviour = null;

    /**
     * see constructor of {@link Creature}
     */
    public NPC(String name, Position position, int sizeX, int sizeY,
               int healthPoints, int attackPoints, int expPoints
    ){
        super(name, position, sizeX, sizeY, healthPoints, attackPoints, expPoints);
    }


    /**
     * {@link Creature}
     * Default action for NPCs' counter attack is simply dealing its attack damage to attacker
     * @param attacker the creature, that this one gets attacked by
     */
    @Override
    public void counterAttack(Creature attacker){
        attacker.addHealthPoints(-this.getAttackPoints());
    }

    /**
     * {@link Creature}
     * Display who the creature was defeated by
     */
    @Override
    public void onDeathAction(){
        System.out.println(this.name + " was defeated and drops " + this.expPoints + " XP points");
    }

    public AggresiveBehaviour getAggresiveBehaviour() {
        return aggresiveBehaviour;
    }

    public void setAggresiveBehaviour(AggresiveBehaviour aggresiveBehaviour) {
        this.aggresiveBehaviour = aggresiveBehaviour;
    }
}
