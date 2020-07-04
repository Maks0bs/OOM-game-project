package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

public class CalmState implements NPCState {
    private double speed = 1d, moveX = 0d, moveY = 0d, defaultMotionAngle = 0d;

    public CalmState(double speed){
        this.speed = speed;
    }

    @Override
    public void stayIdle(World world, NPC npc) {
        if (npc.getHungerPoints() >= 100){
            npc.setState(npc.getSearchingFoodState(), world);
        } else {
            npc.setState(npc.getCalmState(), world);
        }
    }

    @Override
    public void followEntity(World world, NPC npc, Entity toFollow) {
        if (toFollow instanceof Creature){
            Creature creature = (Creature) toFollow;
            if (npc.getAggresiveBehaviour().isAggressiveAgainst(creature)) {
                npc.setState(npc.getAggressiveState(), world);
                npc.getState().followEntity(world, npc, toFollow);
            } else {
                if (npc.getHungerPoints() >= 100){
                    npc.setState(npc.getSearchingFoodState(), world);
                } else {
                    npc.setState(npc.getCalmState(), world);
                }
            }

        } else {
            if (npc.getHungerPoints() >= 100){
                npc.setState(npc.getSearchingFoodState(), world);
            } else {
                npc.setState(npc.getCalmState(), world);
            }
        }

    }

    @Override
    public void runFromEntity(World world, NPC npc, Entity toRunFrom) {
        npc.setState(npc.getAfraidState(), world);
        npc.getState().runFromEntity(world, npc, toRunFrom);
    }

    @Override
    public void onEnter(World world, NPC npc) {
        npc.setHungerPoints(npc.getHungerPoints() + 1);
        int moveXInt, moveYInt;

        moveX += Math.cos(defaultMotionAngle) * speed;
        moveY += Math.sin(defaultMotionAngle) * speed;

        moveXInt = (int) moveX;
        moveYInt = (int) moveY;

        moveX -= moveXInt;
        moveY -= moveYInt;

        defaultMotionAngle += Math.PI / 36;
        if (defaultMotionAngle > 2 * Math.PI) {
            defaultMotionAngle -= 2 * Math.PI;
        }

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
