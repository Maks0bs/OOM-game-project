package com.oom.game.main.environment;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.blocks.EmptyVoid;
import com.oom.game.main.environment.blocks.Grass;
import com.oom.game.main.environment.utils.exceptions.WorldResizeException;
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
    public static final int BLOCK_SIZE = 32;
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
    private ArrayList<Entity> entities = new ArrayList<Entity>();//FIXME save entities in a Set / Map
    /*
        At the moment there can only be one player
     */
    private Player player = null;

    /**
     * @return default world (100x100), filled with 10000 grass blocks
     */
    public static World generateDefaultWorld(){
        World world = new World(100, 100);
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                world.addBlock(new Position(j, i, true), new Grass());
            }
        }

        return world;
    }

    /**
     *
     * @param blockCountY amount of blocks in the world on y-axis
     * @param blockCountX amount of blocks in the world on x-axis
     */
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
        if (position.getBlockY() >= blockCountY || position.getBlockX() >= blockCountX){
            return null;
        }
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
     * @param i position of the block to be added on y-axis
     * @param j position of the block to be added on x-axis
     * @param block new block to be added ON TOP of the current structure (if any).
     *              If the current structure is EmptyVoid {@link Void} then replace this block with this parameter completely
     * @return true is block was added, false if something went wrong
     */
    public boolean addBlock(int i, int j, Block block){
        Block curBlock = this.getBlock(i, j);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            //FIXME it is a bad idea to use instance of, change to smth else
            blocks.get(i).set(j, block);
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

    /**
     * NOTE: deletion shouldn't leave any null pointers. Please use the EmptyVoid {@link Void} block as a placeholder.
     * @param i position of the block to be removed on y-axis
     * @param j position of the block to be removed on x-axis
     * @return true if block was removed successfully, false on error
     */
    public boolean removeBlock(int i, int j){
        Block curBlock = this.getBlock(i, j);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            return false;
        } else if (curBlock.hasBlockOnTop()){

            curBlock.setBlockOnTop(null);
            return true;
        } else {
            blocks.get(i).set(j, new EmptyVoid());
            return true;
        }
    }

    /*
        TODO In order to remove and add entities more easily
        TODO an ID field should be added to all game objects
     */
    /**
     * !!!Note!!! please do not use this method to add players. See addPlayer method;
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
     * Please use only this method to add players. Do not use addEntity for that
     * @param player player to be bound to this world
     * @return true if player was bound successfully, false otherwise
     */
    public boolean addPlayer(Player player){
        if (this.player == null){
            this.player = player;
            return true;
        }
        return false;
    }

    /**
     *
     * @return true if player is bound to this world, false otherwise
     */
    public boolean hasPlayer(){
        return this.player != null;
    }

    /**
     * Unbinds player from current world
     */
    public void removePlayer(){
        this.player = null;
    }


    /**
     *
     * @param position top left position of area to be searched in
     * @param sizeX x-size of search area rectangle
     * @param sizeY y-size of search area rectangle
     * @return {@linkplain ArrayList} of found entities
     */
    public ArrayList<Entity> getEntitiesInArea(Position position, int sizeX, int sizeY){
        //FIXME implement this method
        return null;
    }

    /**
     * FIXME might need to remove this method and do area search manually in caller classes
     * @param position top left position of area to be searched in
     * @param sizeX x-size of search area rectangle
     * @param sizeY y-size of search area rectangle
     * @return list
     */
    public ArrayList<Entity> getBlocksInArea(Position position, int sizeX, int sizeY){
        //FIXME implement this method
        return null;
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

    public Player getPlayer() {
        return player;
    }
}
