package com.oom.game.main.entities;

import com.oom.game.main.environment.Position;

/*
    Entity is a general class for all game game objects, which you can interact with
 */
public abstract class Entity {
    // positions on the 2D game grid
    protected Position position;

    public Entity(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    abstract String getInfo();
}
