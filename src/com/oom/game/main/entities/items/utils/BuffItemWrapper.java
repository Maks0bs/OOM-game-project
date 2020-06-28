package com.oom.game.main.entities.items.utils;

import com.oom.game.main.entities.items.enchantments.AddAttackEnchant;
import com.oom.game.main.entities.items.enchantments.AddHealthEnchant;
import com.oom.game.main.entities.items.enchantments.MultAttackEnchant10;
import com.oom.game.main.entities.items.enchantments.MultHealthEnchant10;

public abstract class BuffItemWrapper extends BuffItem {
    protected BuffItem buffItem;

    public static BuffItem enchantRandomly(BuffItem item){
        int key = (int) (Math.random() * 100);
        if (key < 25){
            return new AddAttackEnchant(item);
        } else if (key < 50){
            return new MultAttackEnchant10(item);
        } else if (key < 75){
            return new AddHealthEnchant(item);
        } else {
            return new MultHealthEnchant10(item);
        }
    }

    /**
     *
     * @param name {@link InventoryItem}
     */
    public BuffItemWrapper(String name){
        super(name);
    }

    /**
     * Default constructor, because BuffItemWrapper cannot be instantiated
     */
    public BuffItemWrapper(){
        super("Default");
    }

    /**
     *
     * @return amount of HPs to add to the wearer
     */
    @Override
    public abstract int getAddHealth();

    /**
     *
     * @return coefficient to multiply the wearers HP
     */
    @Override
    public abstract double getMultHealth();

    /**
     *
     * @return amount of attack points to add to the wearer
     */
    @Override
    public abstract int getAddAttack();

    /**
     *
     * @return coefficient to multiply the wearers attack points
     */
    @Override
    public abstract double getMultAttack();
}
