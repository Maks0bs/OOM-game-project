package com.oom.game.main.environment;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.blocks.EmptyVoid;
import com.oom.game.main.environment.exceptions.WorldResizeException;
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
    public static final int BLOCK_SIZE = 64;
    private int blockCountX, blockCountY;
    /**
        This ArrayList contains game world's coordinates, where inner blocks are Y-coordinates
        and outer ones are X-coordinates.
        It can also be seen as a table with X- and Y-axes.
       y/x   0     1     2    ...
        0   Block Block Block
        1   Block Block Block
        2   Block Block Block
       ...
    */
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public World(int blockCountY, int blockCountX){
        this.blockCountX = blockCountX;
        this.blockCountY = blockCountY;
        for (int i = 0; i < blockCountY; i++) {
            blocks.add(new ArrayList<Block>());
            for (int j = 0; j < blockCountX; j++) {
                blocks.get(i).add(new EmptyVoid()); //Default placeholder block is EmptyVoid
            }
        }
    }

    /**
     *
     * @param position position of the block the user wants to get
     * @return requested block in this world
     */
    public Block getBlock(Position position) {
        return blocks.get(position.getBlockY()).get(position.getBlockX());
    }
    public Block getBlock(int i, int j){
        return blocks.get(i).get(j);
    }

    /**
     * Change the size of the world (increase ArrayList size)
     * @param newBlockCountX obvious :)
     * @param newBlockCountY obvious :)
     * Storing old values in a temporary variable, then replacing the old value with a new one.
     * Then filling an array in a two-dimensional loop with new Blocks.
     *
     */
    public void increaseSize(int newBlockCountX, int newBlockCountY) throws WorldResizeException {
        if (newBlockCountX < this.blockCountX || newBlockCountY < this.blockCountY){
            throw new WorldResizeException("New world constraints are smaller than the previous ones");
        }
        int tempBlockCountX = this.blockCountX; 
        int tempBlockCountY = this.blockCountY;
        this.blockCountX = newBlockCountX;
        this.blockCountY = newBlockCountY;
        for (int i = tempBlockCountY; i < newBlockCountY; i++) {
            blocks.add(new ArrayList<Block>());
            for (int j = tempBlockCountX; j < newBlockCountX; j++) {
                blocks.get(i).add(new EmptyVoid()); //Default placeholder block is EmptyVoid
            }
        }

    }

    /**
     * TODO maybe replace boolean return type with exceptions
     * FIXME apply changes to UML
     * @param position position of the block to be added
     * @param block new block to be added ON TOP of the current structure (if any).
     *              If the current structure is EmptyVoid {@link Void} then replace this block with this parameter completely
     * @return true is block was added, false if something went wrong
     */
    public boolean addBlock(Position position, Block block){
        Block curBlock = this.getBlock(position);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            //FIXME it is a bad idea to use instance of, change to smth else
            blocks.get(position.getBlockY()).set(position.getBlockX(), block);
            return true;
        }
        else{ //otherwise there is a floor block, add one on top, but check if block on top already exists
            if (curBlock.hasBlockOnTop()){
                return false;
            }
            curBlock.setBlockOnTop(block);
            return true;
        }
    }

    /**
     * NOTE: deletion shouldn't leave any null pointers. Please use the EmptyVoid {@link Void} block as a placeholder.
     * @param position position of the block to be removed
     * @return true if block was removed successfully, false on error
     */
    public boolean removeBlock(Position position){
        Block curBlock = this.getBlock(position);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            return false;
        } else if (curBlock.hasBlockOnTop()){

            curBlock.setBlockOnTop(null);
            return true;
        } else {
            blocks.get(position.getBlockY()).set(position.getBlockX(), new EmptyVoid());
            return true;
        }
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
        Position pos = entity.getPosition();
        for (int i = pos.getBlockY(); i <= (pos.getY() + entity.getSizeY()) / BLOCK_SIZE; i++){
            for (int j = pos.getBlockX(); j <= (pos.getX() + entity.getSizeX()) / BLOCK_SIZE; j++){
                Block b = this.getBlock(i, j);
                if (b.getWalkAction() == null || !b.getWalkAction().canWalk()){
                    return false;
                }
            }
        }
        //FIXME get rid of instanceof here somehow
        if (entity instanceof Creature) {
            for (Entity e : this.entities) {
                if ((e instanceof Creature) && e.overlapsWith(entity)) {
                    return false;
                }
            }
        }

        entities.add(entity);
        return true;
    }

    /**
     *
     * @param entity the entity you want to delete from the world.
     * @return true if entity was deleted, false if it wasn't present in the list of entities or on error
     */
    public boolean removeEntity(Entity entity){
        for (int i = 0; i < entities.size(); i++){
            if (entities.get(i) == entity){//here addresses are compared, and that's what wee need, don't use .equals()
                entities.remove(i);
                return true;
            }
        }
        return false;
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

    public ArrayList<ArrayList<Block>> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<ArrayList<Block>> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
