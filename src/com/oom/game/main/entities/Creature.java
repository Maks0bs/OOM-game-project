package com.oom.game.main.entities;

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

    public Creature(String name){
        super();
        this.name = name;
    }


    /*
    TODO reduce constructors for Creature
    TODO add some interfaces for default actions
     */

    public Creature(String name, int healthPoints, int attackPoints){
        super();
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
    }

    public Creature(String name, int healthPoints, int attackPoints, int expPoints){
        super();
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.expPoints = expPoints;
    }

    public Creature(String name, double x, double y) {
        super(x, y);
        this.name = name;
    }

    public Creature(String name, double x, double y, int healthPoints, int attackPoints){
        super(x, y);
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
    }

    public Creature(String name, double x, double y, int healthPoints, int attackPoints, int expPoints){
        super(x, y);
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.expPoints = expPoints;
    }

    public Creature(){
        super();
    }

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

    public void addHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public boolean isAlive(){
        return this.healthPoints > 0;
    }

    public String getInfo(){
        return "Creature: " + this.name;
    }

    abstract void onDeathAction();

    abstract void counterAttack(Creature attacker);
}
