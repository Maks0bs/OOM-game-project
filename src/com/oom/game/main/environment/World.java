package com.oom.game.main.environment;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.Position;

import java.util.ArrayList;

public class World {
    //FIXME add this interface to UML
    public static final int DEFAULT_TILE_COUNT = 64;
    private int blockCountX, blockCountY;
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();

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
     *
     * @param newBlockCountX obvious
     * @param newBlockCountY obvious
     */
    public void resize(int newBlockCountX, int newBlockCountY){
        //FIXME implement this method
    }

    /**
     *
     * @param position position of the block to be added
     * @param block new block to be added
     */
    public void addBlock(Position position, Block block){
        //FIXME implement this method
        //FIXME check if the block is OpenTop and if it is, then add block on top of the current stack
    }

    /**
     *
     * @param position position of the block to be added
     * @param block new block to be updated for
     */
    public void updateBlock(Position position, Block block){
        //FIXME implement this method
        //FIXME check if the block is OpenTop and if it is, then update accordingly (not sure how exactly)
    }


    /**
     *
     * @param position position of the block to be removed
     */
    public void removeBlock(Position position){
        //FIXME implement this method
        //FIXME check if the block is Stackable and if it is, then delete all of its children or delete only the block on top
    }

    /**
     *
     * @param position position of the block you want to put the entity on
     * @param entity the entity you want to add
     */
    public void addEntity(Position position, Entity entity){
        //FIXME implement this method
        //FIXME check if it is legal to add entities to this block!!!
    }
}
