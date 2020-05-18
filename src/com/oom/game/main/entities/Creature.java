package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

/*
    Creature is an entity with stats and some props
 */
public abstract class Creature extends Entity {

    public static final String NAME_UNKNOWN = "CREATURE_NAME_UNKNOWN";
    /*
        Stats of a creature
        After killing a creature, you receive their exp
        NPCs exp value doesn't change. Players can increase their exp by killing Creatures
        (due to ProgressiveCreature interface)
     */
    protected int healthPoints = 0, attackPoints = 0, expPoints = 0;
    protected String name = NAME_UNKNOWN;

    /*
        TODO reduce constructors for Creature
        TODO add some interfaces for default actions
     */

    /**
     * @param name name of the creature
     * @param position {@link Entity}
     * @param sizeX {@link Entity}
     * @param sizeY {@link Entity}
     * @param healthPoints health points of new creature
     * @param attackPoints attack points of new creature
     * @param expPoints experience points of new creature
     */
    public Creature(String name, Position position, int sizeX, int sizeY,
                    int healthPoints, int attackPoints, int expPoints
    ){
        super(position, sizeX, sizeY);
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.expPoints = expPoints;
    }

    /**
     * Method for adding HPs to creature
     * may be as well used to decrease HP
     * should be preferred instead of default setter for HP
     * @param healthPoints HPs to add
     */
    public void addHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    /**
     * Check if creature is alive
     * @return status of create (alive = true, dead = false) depending on the HP amount
     */
    public boolean isAlive(){
        return this.healthPoints > 0;
    }

    /**
     * {@link Entity}
     * @return info of creature
     */
    @Override
    public String getInfo(){
        return "Creature: " + this.name;
    }

    /**
     * Action, that should be performed when the creature dies
     */
    abstract void onDeathAction();

    /**
     * default counter attack action, when the creature is attacked
     * @param attacker the creature, that this one gets attacked by
     */
    abstract void counterAttack(Creature attacker);

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public String getName() {
        return name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setName(String name) {
        this.name = name;
    }
}
