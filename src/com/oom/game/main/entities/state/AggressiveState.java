package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

public class AggressiveState implements NPCState {
    private double speed = 1d, moveX = 0d, moveY = 0d;
    private Entity toFollow = null;

    public AggressiveState(double speed){
        this.speed = speed;
    }

    @Override
    public void stayIdle(World world, NPC npc) {
        if (npc.getEnergyPoints() <= 2) {
            npc.setState(npc.getSleepingState(), world);
        } else if (npc.getHungerPoints() >= 500){
            npc.setState(npc.getSearchingFoodState(), world);
        } else {
            npc.setState(npc.getAggressiveState(), world);
        }
    }

    @Override
    public void followEntity(World world, NPC npc, Entity toFollow) {
        if (toFollow instanceof Creature){
            Creature creature = (Creature) toFollow;
            if (npc.getAggresiveBehaviour().isAggressiveAgainst(creature)){
                this.toFollow = toFollow;
                npc.setState(npc.getAggressiveState(), world);
            } else {
                if (npc.getEnergyPoints() <= 2) {
                    npc.setState(npc.getSleepingState(), world);
                } else if (npc.getHungerPoints() >= 500){
                    npc.setState(npc.getSearchingFoodState(), world);
                } else {
                    npc.setState(npc.getAggressiveState(), world);
                }
            }
        } else {
            if (npc.getEnergyPoints() <= 2) {
                npc.setState(npc.getSleepingState(), world);
            } else if (npc.getHungerPoints() >= 500){
                npc.setState(npc.getSearchingFoodState(), world);
            } else {
                npc.setState(npc.getAggressiveState(), world);
            }
        }
    }

    @Override
    public void runFromEntity(World world, NPC npc, Entity toRunFrom) {
        if (toRunFrom instanceof Creature){
            Creature creature = (Creature) toRunFrom;
            if (npc.getFearBehaviour().isAfraidOf(creature)){
                npc.setState(npc.getAfraidState(), world);
                npc.getState().runFromEntity(world, npc, toRunFrom);
            } else {
                if (npc.getEnergyPoints() <= 2) {
                    npc.setState(npc.getSleepingState(), world);
                } else if (npc.getHungerPoints() >= 500){
                    npc.setState(npc.getSearchingFoodState(), world);
                } else {
                    npc.setState(npc.getAggressiveState(), world);
                }
            }
        } else {
            if (npc.getEnergyPoints() <= 2) {
                npc.setState(npc.getSleepingState(), world);
            } else if (npc.getHungerPoints() >= 500){
                npc.setState(npc.getSearchingFoodState(), world);
            } else {
                npc.setState(npc.getAggressiveState(), world);
            }
        }
    }

    @Override
    public void onEnter(World world, NPC npc) {

        if (toFollow == null){
            return;
        }


        npc.setHungerPoints(npc.getHungerPoints() + 2);
        npc.setEnergyPoints(npc.getEnergyPoints() - 1);

        int moveXInt, moveYInt;
        double base = world.getBlock(npc.getCenterPosition()).getWalkAction().getBaseWalkingSpeed();
        Position vector = toFollow.getPosition().difference(npc.getPosition());
        double dist = Position.dist(npc.getPosition(), toFollow.getPosition());

        if (dist < 0.001){
            // TODO attack the followed victim
        }

        double vectorX = vector.getX() / dist;
        double vectorY = vector.getY() / dist;

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
