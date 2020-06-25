package com.oom.game.main.entities.items.utils;

/**
 * After BuffItems get picked up by a player they cannot be displayed as an WorldItem class anymore, they
 * are only used internally to calculate buff values!!!!!!!
 */
public abstract class BuffItem extends InventoryItem {
    private int addHealth, addAttack;
    private double multHealth, multAttack;


    /**
     *
     * @param name {@link InventoryItem}
     * @param addHealth the amount of health that this item adds
     * @param multHealth the multiplication coefficient of health that this item sets for wearer
     * @param addAttack the amount of attack points that this item adds
     * @param multAttack the multiplication coefficient of attack points that this item sets for wearer
     */
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
