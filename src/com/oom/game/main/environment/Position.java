package com.oom.game.main.environment;

/*
    Added this class to better manage creature movement.
    Might need to move back to normal coords afterwards.
    The problem is that coords most likely have to be int.

 */

public class Position {
    public static final int BLOCK_SIZE = 64, DEFAULT_POSITION = 0;
    private int x = DEFAULT_POSITION, y = DEFAULT_POSITION, z = DEFAULT_POSITION;

    public Position(int x, int y, int z, boolean isBlockPosition){
        if (isBlockPosition){
            x *= BLOCK_SIZE;
            y *= BLOCK_SIZE;
            z *= BLOCK_SIZE;
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getBlockX(){
        return x / BLOCK_SIZE;
    }

    public int getBlockY(){
        return x / BLOCK_SIZE;
    }
}
