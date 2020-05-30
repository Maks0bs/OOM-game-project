package com.oom.game.main.environment;

/*
    Added this class to better manage creature movement.
    Might need to move back to normal coords afterwards.
    The problem is that coords most likely have to be int.

 */


public class Position{
    public static final int DEFAULT_POSITION = 0;
    private int x = DEFAULT_POSITION, y = DEFAULT_POSITION;


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
     * FIXME add this method to UML
     * @param diff the position to remove from this one
     * @return the result of subtraction of diff position from current one (or vector from this to diff)
     */
    public Position difference(Position diff){
        return new Position(this.getX() - diff.getX(), this.getY() - diff.getY());
    }

    /**
     * FIXME add this method to UML
     * @param diff the position to add to this one
     * @return the result of addition of diff position to the current one
     */
    public Position sum(Position diff){
        return new Position(this.getX() + diff.getX(), this.getY() + diff.getY());
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
