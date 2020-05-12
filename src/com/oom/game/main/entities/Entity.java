package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

/*
    Entity is a general class for all game game objects, which you can interact with
    Like creatures, items,
 */
public abstract class Entity {
    // position of left top corner
    protected Position position;
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
     * FIXME add this method to UML
     * @param entity the entity you want to check if the current one overlaps with
     * @return true if entities overlap, false otherwise
     */
    public boolean overlapsWith(Entity entity){
        int x = entity.getPosition().getX(), y = entity.getPosition().getY(),
                sx = entity.getSizeX(), sy = entity.getSizeY(),
                x0 = this.position.getX(), y0 = this.position.getY();

        //FIXME this doesn't work right, fix this plz

        return !( (x > x0 + this.sizeX) || (x + sx < x0) || (y > y0) || (y + sy < y0));
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
