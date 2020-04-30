package com.oom.game.main.entities;

/*
    Entity is a general class for all game game objects, which you can interact with
 */
public abstract class Entity {
    // positions on the 2D game grid
    protected double x = 0.0, y = 0.0;

    public Entity(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Entity(){

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    abstract String getInfo();
}
