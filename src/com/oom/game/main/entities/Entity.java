package com.oom.game.main.entities;

import com.oom.game.main.environment.utils.Position;

/*
    Entity is a general class for all game game objects, which you can interact with
 */
public abstract class Entity {
    // positions on the 2D game grid
    protected Position position;

    /**
     * FIXME add this constructor to UML diagram
     * @param position position of new entity
     */
    public Entity(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Default info (mainly for console output) of entity
     * @return default info of entity
     */
    abstract String getInfo();
}
