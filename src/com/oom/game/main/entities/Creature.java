package com.oom.game.main.entities;

import com.oom.game.main.entities.mobs.Rabbit;
import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.environment.Position;

/**
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

    /**
     * TODO improve random generation
     * @return random creature (currently 2/3 chance = rabbit, 1/3 chance = wolf)
     */
    public static Creature generateRandomCreature(){
        int key = (int) (Math.random() * 100);
        if (key <= 66){
            return new Rabbit(new Position(0, 0, true));
        } else { //here new possible creatures can be added
            return new Wolf(new Position(0, 0, true));
        }
    }

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
    public Creature(String name, Position position, int sizeX, int sizeY, String state,
                    int healthPoints, int attackPoints, int expPoints
    ){
        super(position, sizeX, sizeY, state);
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
    public abstract void onDeathAction();

    /**
     * default counter attack action, when the creature is attacked
     * @param attacker the creature, that this one gets attacked by
     */
    public abstract void counterAttack(Creature attacker);

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }

}
