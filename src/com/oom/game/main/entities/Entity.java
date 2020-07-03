package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.utils.GameObservable;

import java.io.Serializable;

/**
    Entity is a general class for all game game objects, which you can interact with
    Like creatures, items,
 */
public abstract class Entity implements Serializable {
    // position of left top corner
    protected String appearance = "Default";
    protected Position position;
    /**
        Clarification for sizeX and sizeY:
        the entity takes up pixels on x-axis
        from position.getX() to (position.get.getX() + sizeX - 1), both inclusive; The same for sizeY;
        sizeX is simply the amount of pixels it takes up, but the pixel with coords
        (position.getX(), position.getY()) is also a pixel, so we have to count it.
        Furthermore, the coords calculation starts with 0
     */
    protected int sizeX, sizeY;

    protected GameObservable<Entity> observable = new GameObservable<Entity>();

    /**
     * @param position position of new entity
     * @param sizeX size of entity on the x-axis
     * @param sizeY size of entity on the y-axis
     */
    public Entity(Position position, int sizeX, int sizeY, String appearance) {
        this.position = position;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.appearance = appearance;
    }

    public static final Entity DUMMY = new Entity(
            new Position(0, 0), 2, 2, "Dummy"
    ) {
        @Override
        public String getInfo() {
            return "Dummy";
        }
    };

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
     * @param pos position of the block
     * @return true if block overlaps with current entity
     */
    public boolean overlapsWithBlock(Position pos){
        int x = pos.getX(), y = pos.getY(),
                sx = World.BLOCK_SIZE, sy = World.BLOCK_SIZE,
                x0 = this.position.getX(), y0 = this.position.getY();
        return !( (x > x0 + this.sizeX - 1) || (x + sx - 1 < x0) || (y > y0 + this.sizeY - 1) || (y + sy - 1 < y0));
    }

    /**
     * @param dx change of current entity by x-axis
     * @param dy change of current entity by y-axis
     */
    public void move(int dx, int dy){
        this.position.setX(this.position.getX() + dx);
        this.position.setY(this.position.getY() + dy);
        this.observable.notifyObservers(this);
    }

    /**
     *
     * @return the position right in the middle of the current entity
     */
    public Position getCenterPosition(){
        return new Position(position.getX() + sizeX / 2, position.getY() + sizeY / 2);
    }

    /**
     * Default info (mainly for console output) of entity
     * @return default info of entity
     */
    public abstract String getInfo();

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

    /**
     *
     * @param position position to shallow copy and set for current entity
     */
    public void setPosition(Position position) {
        this.position = position;
        this.observable.notifyObservers(this);
    }

    /**
     *
     * @param position position to deep copy and set for current entity
     */
    public void setPositionDeep(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
        this.observable.notifyObservers(this);

    }


    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
        this.observable.notifyObservers(this);
    }

    public GameObservable<Entity> getObservable() {
        return observable;
    }

}
