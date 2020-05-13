package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

/*
    Entity is a general class for all game game objects, which you can interact with
    Like creatures, items,
 */
public abstract class Entity {
    // position of left top corner
    protected Position position;
    /*
        Clarification for sizeX and sizeY:
        the entity takes up pixels on x-axis
        from position.getX() to (position.get.getX() + sizeX - 1), both inclusive; The same for sizeY;
        sizeX is simply the amount of pixels it takes up, but the pixel with coords
        (position.getX(), position.getY()) is also a pixel, so we have to count it.
        Furthermore, the coords calculation starts with 0
     */
    protected int sizeX, sizeY;

    /**
     * @param position position of new entity
     * @param sizeX size of entity on the x-axis
     * @param sizeY size of entity on the y-axis
     */
    public Entity(Position position, int sizeX, int sizeY) {
        this.position = position;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * @param entity the entity you want to check if the current one overlaps with
     * @return true if entities overlap, false otherwise
     */
    public boolean overlapsWith(Entity entity){
        int x = entity.getPosition().getX(), y = entity.getPosition().getY(),
                sx = entity.getSizeX(), sy = entity.getSizeY(),
                x0 = this.position.getX(), y0 = this.position.getY();


        return !( (x > x0 + this.sizeX - 1) || (x + sx - 1 < x0) || (y > y0 + this.sizeY - 1) || (y + sy - 1 < y0));
    }

    /**
     * Default info (mainly for console output) of entity
     * @return default info of entity
     */
    abstract String getInfo();

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
