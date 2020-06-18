package com.oom.game.main.environment;

import java.io.Serializable;

/**
    Added this class to better manage entity control.
 */
public class Position implements Serializable {
    public static final int DEFAULT_POSITION = 0;
    private int x = DEFAULT_POSITION, y = DEFAULT_POSITION;


    /**
     *
     * @param pos1 first point
     * @param pos2 second point
     * @return distance between 2 given points
     */
    public static double dist(Position pos1, Position pos2){
        double diffX = pos1.getX() - pos2.getX();
        double diffY = pos1.getY() - pos2.getY();

        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    /**
     *
     * @param x x-coordinate of current object
     * @param y y-coordinate of object
     * @param isBlockPosition true if the given coordinates point to a block position,
     *                        false if the given coordinates point to exact position (e. g. for entities)
     */
    public Position(int x, int y, boolean isBlockPosition){
        if (isBlockPosition){
            x *= World.BLOCK_SIZE;
            y *= World.BLOCK_SIZE;
        }
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param x x-coordinate of current object
     * @param y y-coordinate of object
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * This constructor is used to deep clone an existing position
     * @param position position to clone
     */
    public Position(Position position){
        this.x = position.getX();
        this.y = position.getY();
    }

    /**
     * @param diff the position to remove from this one
     * @return the result of subtraction of diff position from current one (or vector from this to diff)
     */
    public Position difference(Position diff){
        return new Position(this.getX() - diff.getX(), this.getY() - diff.getY());
    }

    /**
     * @param diff the position to add to this one
     * @return the result of addition of diff position to the current one
     */
    public Position sum(Position diff){
        return new Position(this.getX() + diff.getX(), this.getY() + diff.getY());
    }

    /**
     *
     * @return block position relative to the current pos (e. g. for (56, 65) the block position is (32, 64) )
     */
    public Position getBlockPosition(){
        int x = this.getBlockX();
        int y = this.getBlockY();
        return new Position(x, y, true);
    }

    /**
     *
     * @return x-coordinate of block by the given exact x-coordinate
     */
    public int getBlockX(){
        return x / World.BLOCK_SIZE;
    }

    /**
     *
     * @return y-coordinate of block by the given exact y-coordinate
     */
    public int getBlockY(){
        return y / World.BLOCK_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;

    }

    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y;
    }
}
