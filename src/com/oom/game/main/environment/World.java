package com.oom.game.main.environment;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.utils.Block;

import java.util.ArrayList;

/*
    !!!IMPORTANT NOTICE!!!
    It is not a good practice to have a lot of stacked blocks (e. g. 3 stacked barrels on a grass block)
    Even though it is possible to achieve internally, it wouldn't be visible in the GUI.
    Furthermore, after some research i have found no such 2D games that implement multi-level block stacking.
    So you can at most have 1 basic block, that you can put others on (OpenTop) and on top of this one
    !!!not more than one block!!!. Example: barrel on a grass block, but no barrels on that first barrel!
 */

public class World {
    public static final int DEFAULT_TILE_COUNT = 64;
    private int blockCountX, blockCountY;
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public World(int blockCountX, int blockCountY){
        this.blockCountX = blockCountX;
        this.blockCountY = blockCountY;
    }


    public int getBlockCountX() {
        return blockCountX;
    }

    public void setBlockCountX(int blockCountX) {
        this.blockCountX = blockCountX;
    }

    public int getBlockCountY() {
        return blockCountY;
    }

    public void setBlockCountY(int blockCountY) {
        this.blockCountY = blockCountY;
    }

    /**
     *
     * @param position position of the block the user wants to get
     * @return requested block in this world
     */
    public Block getBlock(Position position) {
        return blocks.get(position.getBlockY()).get(position.getBlockX());
    }

    /**
     * Change the size of the world (increase / decrease ArrayList size)
     * @param newBlockCountX obvious :)
     * @param newBlockCountY obvious :)
     */
    public void resize(int newBlockCountX, int newBlockCountY){
        //FIXME implement this method
    }

    /**
     *
     * @param position position of the block to be added
     * @param block new block to be added ON TOP of the current structure (if any).
     *              If the current structure is Void {@link Void} then replace this block with this parameter completely
     */
    public void addBlock(Position position, Block block){
        //FIXME implement this method
    }

    /**
     *
     * @param position position of the block to be updated
     * @param block new block that the current one should be replaced with
     * @param onlyTop if true, that only the block on top updates (irrelevant if there are no blocks on top)
     *                if false, then the whole block structure should be rewritten.
     *                For example: if you call this function with onlyTop == false
     *                and replace a grass block with campfire for a sand block with a barrel on it,
     *                then the grass block and campfire get deleted completely and replaced with
     *                the new structure
     */
    public void updateBlock(Position position, Block block, boolean onlyTop){
        //FIXME implement this method
    }


    /**
     * NOTE: deletion shouldn't leave any null pointers. Please use the Void {@link Void} block as a placeholder.
     * @param position position of the block to be removed
     * @param onlyTop if true, that only the block on top gets deleted (irrelevant if there are no blocks on top)
     *                if false, then the whole block structure should be deleted.
     *                For example: if you call this function with onlyTop == false
     *                and delete a grass block with campfire for a sand block with a barrel on it,
     *                then the grass block and campfire get deleted completely and replaced with
     *                the new structure
     */
    public void removeBlock(Position position, boolean onlyTop){
        //FIXME implement this method
    }

    /*
        TODO In order to remove and add entities more easily
        TODO an ID field should be added to all game objects
     */
    /**
     *
     * @param entity the entity you want to add. Should only be added on walkable blocks!!!
     * @return true if it is legal to insert entity in current position, false otherwise
     */
    public boolean addEntity(Entity entity){ //the data of entity already has position in it, no need to pass it as argument
        //FIXME implement this method
        //FIXME check if it is legal to add entities to this block!!!
        return true;
    }

    /**
     *
     * @param entity the entity you want to delete from the world.
     * @return true if it is legal to delete entity  , false otherwise
     */
    public boolean removeEntity(Entity entity){
        //FIXME implement this method
        //FIXME check if it is legal to remove entities
        return true;
    }
}
