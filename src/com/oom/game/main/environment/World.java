package com.oom.game.main.environment;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.OpenBottom;

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
    //FIXME add this class to UML
    public static final int DEFAULT_TILE_COUNT = 64;
    private int blockCountX, blockCountY;
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
    /*
        This ArrayList contains game world's coordinates, where inner blocks are Y-coordinates
        and outer ones are X-coordinates.
        It can also be seen as a table with X- and Y-axes.
       y/x   0     1     2    ...
        0   Block Block Block
        1   Block Block Block
        2   Block Block Block
       ...
     */

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
     * Change the size of the world (increase ArrayList size)
     * @param newBlockCountX obvious :)
     * @param newBlockCountY obvious :)
     * Storing old values in a temporary variable, then replacing the old value with a new one.
     * Then filling an array in a two-dimensional loop with new Blocks.
     *
     */
    public void increaseWorld(int newBlockCountX, int newBlockCountY){
        int tempBlockCountX = this.blockCountX; 
        int tempBlockCountY = this.blockCountY;
        this.blockCountX = newBlockCountX;
        this.blockCountY = newBlockCountY;
        for (int i = tempBlockCountY; i < newBlockCountY; i++) {
            blocks.add(new ArrayList<Block>());
            for (int j = tempBlockCountX; j < newBlockCountX; j++) {
                blocks.get(i).add(new Block(1)); // parameter texture is intentionally replaced with 1
                                                        // so that the code does not lose its functionality
            }
        }

    }

    /**
     *
     * @param position position of the block to be added
     * @param block new block to be added ON TOP of the current structure (if any).
     *              If the current structure is Void {@link Void} then replace this block with this parameter completely
     */
    public void addBlock(Position position, Block block){
        // if (blocks.get((OpenBottom.class.isAssignableFrom()))); will finish it a bit later
        blocks.get(position.getY()).set(position.getX(), block); // replaces the current block
                                                                // as I do not really now how to implement it on top
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
        if (!onlyTop) blocks.get(position.getY()).set(position.getX(), block);
        else; //the other case, where I do not know, what to do, as we have not discussed it yet :)
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
        blocks.get(position.getY()).remove(position.getX());
    }

    /**
     *
     * @param entity the entity you want to add. Should only be added on walkable blocks!!!
     */
    public void addEntity(Entity entity){ //the data of entity already has position in it, no need to pass it as argument
        //FIXME implement this method
        //FIXME check if it is legal to add entities to this block!!!
    }
}
