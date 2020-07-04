package com.oom.game.main.entities;

import com.oom.game.main.entities.interaction.AggressiveBehaviour;
import com.oom.game.main.entities.interaction.FearBehaviour;
import com.oom.game.main.entities.mobs.strategies.NoneAggresion;
import com.oom.game.main.entities.mobs.strategies.NoneFear;
import com.oom.game.main.entities.state.NPCState;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;

/**
 * NPC have some props that players should not have and vice versa
 */
public abstract class NPC extends Creature {
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
    protected NPCState state;


    /**
     * Allows to safely move the npc to the given direction, so that the NPC doesn't get on any
     * unwalkable blocks and smoothly moves further
     * @param world world to perform moving action in
     * @param npc npc that needs to be moved
     * @param moveXInt amount of pixels to move in x direction
     * @param moveYInt amount of pixels to move in y direction
     */
    public static void safelyMove(World world, NPC npc, int moveXInt, int moveYInt){
        npc.move(moveXInt, 0);
        Position blockPosNew = new Position(
                npc.getPosition().getX() + (npc.getSizeX() / 2),
                npc.getPosition().getY() + (npc.getSizeY() / 2)
        );
        Block blockUnderNew = world.getBlock(blockPosNew);
        if (!blockUnderNew.getWalkAction().canWalk()){
            npc.move(-moveXInt, 0);
        }

        npc.move(0, moveYInt);
        blockPosNew = new Position(
                npc.getPosition().getX() + (npc.getSizeX() / 2),
                npc.getPosition().getY() + (npc.getSizeY() / 2)
        );
        blockUnderNew = world.getBlock(blockPosNew);
        if (!blockUnderNew.getWalkAction().canWalk()){
            npc.move(0, -moveYInt);
        }
    }

    /**
     * see constructor of {@link Creature}
     */
    public NPC(String name, Position position, int sizeX, int sizeY, String appearance,
               int healthPoints, int attackPoints, int expPoints
    ){
        super(name, position, sizeX, sizeY, appearance, healthPoints, attackPoints, expPoints);
    }

    public NPCState getState() {
        return state;
    }

    public void setState(NPCState state, World world) {
        if(state == null) {
            return;
        }
        if(this.state != null)
            this.state.onExit(world, this);
        this.state = state;
        this.state.onEnter(world, this);
    }

    // These are the abstract states that each instance of npc should implement and define
    public abstract NPCState getCalmState();
    public abstract NPCState getSearchingFoodState();
    public abstract NPCState getSleepinState();
    public abstract NPCState getAggressiveState();
    public abstract NPCState getAfraidState();

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

    public FearBehaviour getFearBehaviour() {
        return fearBehaviour;
    }

    public AggressiveBehaviour getAggresiveBehaviour() {
        return aggresiveBehaviour;
    }

}
