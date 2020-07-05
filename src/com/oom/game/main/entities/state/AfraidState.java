package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

public class AfraidState implements NPCState {
    private double speed = 1d, moveX = 0d, moveY = 0d;
    private Entity toRunFrom = null;

    public AfraidState(double speed){
        this.speed = speed;
    }

    @Override
    public void stayIdle(World world, NPC npc) {
        if (npc.getEnergyPoints() <= 2){
            npc.setState(npc.getSleepingState(), world);
        } else {
            npc.setState(npc.getAfraidState(), world);
        }
    }

    @Override
    public void followEntity(World world, NPC npc, Entity toFollow) {
        if (npc.getEnergyPoints() <= 2){
            npc.setState(npc.getSleepingState(), world);
        } else {
            npc.setState(npc.getAfraidState(), world);
        }
    }

    @Override
    public void runFromEntity(World world, NPC npc, Entity toRunFrom) {
        if (toRunFrom instanceof Creature){
            Creature creature = (Creature) toRunFrom;
            if (npc.getFearBehaviour().isAfraidOf(creature)){
                this.toRunFrom = toRunFrom;
                npc.setState(npc.getAfraidState(), world);
            } else {
                if (npc.getEnergyPoints() <= 2){
                    npc.setState(npc.getSleepingState(), world);
                } else {
                    npc.setState(npc.getCalmState(), world);
                }
            }
        } else {
            if (npc.getEnergyPoints() <= 2){
                npc.setState(npc.getSleepingState(), world);
            } else {
                npc.setState(npc.getCalmState(), world);
            }
        }
    }

    @Override
    public void onEnter(World world, NPC npc) {
        if (toRunFrom == null){

            return;
        }

        npc.setHungerPoints(npc.getHungerPoints() + 1);
        npc.setEnergyPoints(npc.getEnergyPoints() - 2);

        int moveXInt, moveYInt;
        double base = world.getBlock(npc.getCenterPosition()).getWalkAction().getBaseWalkingSpeed();
        Position vector = toRunFrom.getPosition().difference(npc.getPosition());
        double dist = Position.dist(npc.getPosition(), toRunFrom.getPosition());

        double vectorX = - (vector.getX() / dist);
        double vectorY = - (vector.getY() / dist);

        if (Double.isNaN(vectorX) || Double.isNaN(vectorY)){
            return;
        }

        moveX += vectorX * speed * base;
        moveY += vectorY * speed * base;

        moveXInt = (int) moveX;
        moveYInt = (int) moveY;

        moveX -= moveXInt;
        moveY -= moveYInt;


        NPC.safelyMove(world, npc, moveXInt, moveYInt);
    }

    @Override
    public void onExit(World world, NPC npc) {

    }

    @Override
    public double getSpeed() {
        return speed;
    }
}
