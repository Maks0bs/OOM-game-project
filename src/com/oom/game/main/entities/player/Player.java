package com.oom.game.main.entities.player;

import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.items.Backpack;
import com.oom.game.main.entities.items.utils.*;
import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.interaction.ProgressiveCreature;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;


public class Player extends Creature implements ProgressiveCreature {
    public static String DEFAULT_APPEARANCE = "PlayerDefault", NAME = "Player";
    public static int DEFAULT_HEALTH_POINTS = 100, DEFAULT_ATTACK_POINTS = 5;
    //TODO MAYBE IT WOULD BE NECESSARY TO ADD WORLD REFERENCE FOR PLAYER TO KNOW POSITION AND BLOCK UNDER HIM, ETC.
    private final InventoryContainer inventory = new Backpack();
    private BuffItem weapon = null, amulet = null;

    public static Player generateDefaultPlayer(){
        return new Player(NAME, new Position(0, 0, true),
                World.BLOCK_SIZE, World.BLOCK_SIZE,
                DEFAULT_APPEARANCE, DEFAULT_HEALTH_POINTS, DEFAULT_ATTACK_POINTS, 0
        );
    }

    /**
     *
     * {@link Creature}
     */
    public Player(String name, Position position, int sizeX, int sizeY, String appearance,
                  int healthPoints, int attackPoints, int expPoints
    ){
        super(name, position, sizeX, sizeY, appearance, healthPoints, attackPoints, expPoints);
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
            this.addExpPoints(victim.getExpPoints());
            for (int i = prevLevel + 1; i <= this.getLevel(); i++){
                this.onLevelUp(i);
            }
            return;
        }
        victim.counterAttack(this);
    }

    /**
     * Gets fired when F key is pressed
     * Adds item to the inventory if it isn't full
     * Replaces current weapon with the given one
     */
    public void pickUpItem(WorldItem item){
        if (inventory.getAmount() < inventory.getMaxCapacity()){
            inventory.add(item.getInventoryItem());
            if (item.getInventoryItem() instanceof BuffItem){
                weapon = (BuffItem) item.getInventoryItem();
                this.setAppearance("WeaponizedPlayer");
            }
        }

        this.observable.notifyObservers(this);


        System.out.println(
                "New stats: \n" +
                "HP: " + this.getHealthPoints() + "\n" +
                "ATK: " + this.getAttackPoints()
        );
    }

    /**
     * FIXME this method is going to be removed. This is temporary, to fulfill Uebungsblatt 5
     * Enchant current weapon with random enchantment
     */
    public void enchantWeaponRandomly(){
        if (weapon == null){
            return;
        }
        weapon = BuffItemWrapper.enchantRandomly(weapon);
        System.out.println(
            "New stats: \n" +
                    "HP: " + this.getHealthPoints() + "\n" +
                    "ATK: " + this.getAttackPoints()
        );
    }

    /**
     * Replaces current amulet with the given one
     */
    public void pickUpAmulet(WorldItem item){
        inventory.add(item.getInventoryItem());
        //TODO implement this
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


    @Override
    public int getHealthPoints() {
        if (weapon != null){
            return super.getHealthPoints() + (int) ((double)weapon.getAddHealth() * weapon.getMultHealth());
        } else{
            return super.getHealthPoints();
        }
    }

    @Override
    public int getAttackPoints() {
        if (weapon != null){
            return super.getAttackPoints() + (int) ((double)weapon.getAddAttack() * weapon.getMultAttack());
        } else{
            return super.getAttackPoints();
        }
    }

    public InventoryContainer getInventory() {
        return inventory;
    }
}
