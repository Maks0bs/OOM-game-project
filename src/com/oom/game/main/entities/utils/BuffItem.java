package com.oom.game.main.entities.utils;

import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.utils.InventoryItem;
import com.oom.game.main.environment.Position;

/**
 * After BuffItems get picked up by a player they cannot be displayed as an WorldItem class anymore, they
 * are only used internally to calculate buff values!!!!!!!
 */
public abstract class BuffItem extends InventoryItem {
    private int addHealth, addAttack;
    private double multHealth, multAttack;


    public BuffItem(String name, int addHealth, double multHealth, int addAttack, double multAttack){
        super(name);
        this.addHealth = addHealth;
        this.multHealth = multHealth;
        this.addAttack = addAttack;
        this.multAttack = multAttack;
    }

    /**
     * Default constructor (BuffItem cannot be instantiated)
     * {@link InventoryItem}
     */
    public BuffItem(String name){
        super(name);
    }

    public int getAddHealth() {
        return addHealth;
    }

    public void setAddHealth(int addHealth) {
        this.addHealth = addHealth;
    }

    public int getAddAttack() {
        return addAttack;
    }

    public void setAddAttack(int addAttack) {
        this.addAttack = addAttack;
    }

    public double getMultHealth() {
        return multHealth;
    }

    public void setMultHealth(double multHealth) {
        this.multHealth = multHealth;
    }

    public double getMultAttack() {
        return multAttack;
    }

    public void setMultAttack(double multAttack) {
        this.multAttack = multAttack;
    }
}
