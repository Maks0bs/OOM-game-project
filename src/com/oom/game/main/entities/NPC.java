package com.oom.game.main.entities;

import com.oom.game.main.entities.interaction.AggressiveBehaviour;
import com.oom.game.main.entities.interaction.FearBehaviour;
import com.oom.game.main.entities.mobs.strategies.NoneAggresion;
import com.oom.game.main.entities.mobs.strategies.NoneFear;
import com.oom.game.main.environment.Position;
import com.oom.game.main.process.utils.control.NPCMovement;

public class NPC extends Creature {
    /*
        In this case this.expPoints is the reward
        that players receive after defeating the NPC
     */

    //FIXME refactor creatures and entities with strategy pattern

    public static final int DEFAULT_EXP_KILL_REWARD = 5;

    /*
     *  FIXME when strategies in this class change, a special method should be called
     *  FIXME to notify the creature (the same for block) to change its texture
     */
    protected AggressiveBehaviour aggresiveBehaviour = new NoneAggresion();
    protected FearBehaviour fearBehaviour = new NoneFear();
    protected NPCMovement movement = null;

    /**
     * see constructor of {@link Creature}
     */
    public NPC(String name, Position position, int sizeX, int sizeY, String state,
               int healthPoints, int attackPoints, int expPoints
    ){
        super(name, position, sizeX, sizeY, state, healthPoints, attackPoints, expPoints);
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

    public AggressiveBehaviour getAggresiveBehaviour() {
        return aggresiveBehaviour;
    }



    public void setAggresiveBehaviour(AggressiveBehaviour aggresiveBehaviour) {
        this.aggresiveBehaviour = aggresiveBehaviour;
    }

    public NPCMovement getMovement() {
        return movement;
    }

    public void setMovement(NPCMovement movement) {
        this.movement = movement;
    }
}
