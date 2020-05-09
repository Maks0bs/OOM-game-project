package com.oom.game.main.environment;

import java.util.ArrayList;

public class World {
    public static final int DEFAULT_TILE_COUNT = 64;
    private int blockCountX, blockCountY;
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>()

    public World(int blockCountX, int blockCountY){
        this.blockCountX = blockCountX;
        this.blockCountY = blockCountY;

        /*
            This kind of putting empty values into array is disguisting
            there might be another, better way.
         */
        ArrayList<Block> emptyRow = new ArrayList<Block>();
        for (int i = 0; i < blockCountX; i++){
            emptyRow.add(new Block());
        }
        for (int i = 0; i < blockCountY; i++){
            blocks.add(emptyRow);
        }
    }

    public World(){

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



    public void resize(/*new size*/);

    public void updateBlockOnTop(/*position, ?stackable? block to change*/);

    public void addBlockOnTop(/*position, ?stackable? block to put on top*/);

    public void removeBlockOnTop(/*position, ?stackable? block to remove from top*/);

    public void addEntity(/*position, entity*/);
    /*
        There can only be one creature on a block
    */
}
