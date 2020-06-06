package com.oom.game.main.entities;

import com.oom.game.main.entities.utils.InventoryItem;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;


/**
 *
 * WorldItem is the Entity that is going to be displayed in the world.
 * When picked up or used, only the properties of the correspondent InventoryItem are used!!!!!
 */
public class WorldItem extends Entity {

    private boolean isLyingOnFloor = true;
    private InventoryItem inventoryItem;

    //All items are 16x16 renderables by default

    public WorldItem(Position position, int sizeX, int sizeY, String state, InventoryItem inventoryItem){
        //super(position, sizeX, sizeY, state);
        super(position, sizeX, sizeY, state);
        this.inventoryItem = inventoryItem;
    }

    public WorldItem(Position position, String state, InventoryItem inventoryItem){
        //super(position, sizeX, sizeY, state);
        super(position, World.BLOCK_SIZE / 2, World.BLOCK_SIZE / 2, state);
        this.inventoryItem = inventoryItem;
    }


    @Override
    String getInfo() {
        return null;
    }

    public boolean isLyingOnFloor() {
        return isLyingOnFloor;
    }

    public void setLyingOnFloor(boolean lyingOnFloor) {
        isLyingOnFloor = lyingOnFloor;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
}
