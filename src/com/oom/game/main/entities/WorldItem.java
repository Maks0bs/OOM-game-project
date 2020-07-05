package com.oom.game.main.entities;

import com.oom.game.main.entities.items.utils.InventoryComponent;
import com.oom.game.main.entities.items.utils.InventoryItem;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;


/**
 *
 * WorldItem is the Entity that is going to be displayed in the world.
 * When picked up or used, only the properties of the correspondent InventoryItem are used!!!!!
 */
public class WorldItem extends Entity {

    private boolean isLyingOnFloor = true;
    private InventoryComponent inventoryItem;

    //All items are 16x16 renderables

    /**
     *
     * {@link Entity}
     * @param inventoryItem inner inventory item for working with item and inventory logic
     */
    public WorldItem(Position position, String appearance, InventoryComponent inventoryItem){
        super(position, World.BLOCK_SIZE / 2, World.BLOCK_SIZE / 2, appearance);
        this.inventoryItem = inventoryItem;
    }


    @Override
    public String getInfo() {
        return null;
    }

    public boolean isLyingOnFloor() {
        return isLyingOnFloor;
    }

    public void setLyingOnFloor(boolean lyingOnFloor) {
        isLyingOnFloor = lyingOnFloor;
    }

    public InventoryComponent getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
}
