package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

public class Player extends Creature implements ProgressiveCreature{
    /*
        Players exp influence their level
     */
    public Player(String name, Position position, int healthPoints, int attackPoints, int expPoints){
        super(name, position, healthPoints, attackPoints, expPoints);
    }

    /*
        The process of attacking and counterattacking
        TODO may be influenced by various effects of attacker / victim
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

    public void counterAttack(Creature attacker){
        attacker.addHealthPoints(-this.getAttackPoints());
    }

    public void addExpPoints(int exp){
        this.expPoints += exp;
    }

    /*
        This function might be used in feature version for achievements, etc. That's why it's already
        in the interface.
        TODO currently level system is linear
        TODO might need to change to gradual
     */
    public int getLevel() {
        return this.expPoints / 10;
    }

    public void onLevelUp(int level){
        System.out.println("Player " + this.name + " has gained level " + level);
    }

    public void onDeathAction(){
        System.out.println("Player " + this.name + " has perished :(");
    }

    @Override
    public String getInfo() {
        return
            "Player: " + this.name + "\n" +
            "Level: " + this.getLevel() + "\n" +
            "HP: " + this.getHealthPoints();
    }

    public void displayProgress() {
        System.out.println(this.getInfo());
    }
}
