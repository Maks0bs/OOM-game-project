package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

public class Player extends Creature implements ProgressiveCreature{
    /*
        Players exp influence their level
     */
    /**
     * see constructor of {@link Creature}
     */
    public Player(String name, Position position, int sizeX, int sizeY,
                  int healthPoints, int attackPoints, int expPoints
    ){
        super(name, position, sizeX, sizeY, healthPoints, attackPoints, expPoints);
    }

    /*
        The process of attacking and counterattacking
        TODO may be influenced by various effects of attacker / victim
     */

    /**
     * Basic player attack functionality
     * @param victim the creature that this player wants to attack
     */
    public void attack(Creature victim){
        victim.addHealthPoints(-this.attackPoints);
        if (!victim.isAlive()){
            victim.onDeathAction();
            int prevLevel = this.getLevel();
            this.addExpPoints(victim.expPoints);
            for (int i = prevLevel + 1; i <= this.getLevel(); i++){
                this.onLevelUp(i);
            }
            return;
        }
        victim.counterAttack(this);
    }

    /**
     * {@link Creature}
     * @param attacker the creature, that this one gets attacked by
     */
    @Override
    public void counterAttack(Creature attacker){
        attacker.addHealthPoints(-this.getAttackPoints());
    }

    /**
     * {@link ProgressiveCreature}
     * @param exp experience points to be added
     */
    @Override
    public void addExpPoints(int exp) {

    }

    /**
     * TODO currently level system is linear
     * TODO might need to change to gradual
     * {@link ProgressiveCreature}
     * @return current level of player
     */
    @Override
    public int getLevel() {
        return this.expPoints / 10;
    }

    /**
     * {@link ProgressiveCreature}
     * @param level current level of player
     */
    @Override
    public void onLevelUp(int level){
        System.out.println("Player " + this.name + " has gained level " + level);
    }

    /**
     * {@link Creature}
     */
    @Override
    public void onDeathAction(){
        System.out.println("Player " + this.name + " has perished :(");
    }


    /**
     * {@link Entity}
     * @return basic stringified player info
     */
    @Override
    public String getInfo() {
        return
            "Player: " + this.name + "\n" +
            "Level: " + this.getLevel() + "\n" +
            "HP: " + this.getHealthPoints();
    }

    /**
     * {@link ProgressiveCreature}
     */
    @Override
    public void displayProgress() {
        System.out.println(this.getInfo());
    }
}
