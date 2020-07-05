package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

public class SearchingFoodState implements NPCState {
    private double speed = 1d, moveX = 0d, moveY = 0d;
    private Entity toFollow = null;

    public SearchingFoodState(double speed){
        this.speed = speed;
    }

    @Override
    public void stayIdle(World world, NPC npc) {

        if (npc.getEnergyPoints() <= 2){
            npc.setState(npc.getSleepingState(), world);
        } else {
            npc.setState(npc.getSearchingFoodState(), world);
        }
    }

    @Override
    public void followEntity(World world, NPC npc, Entity toFollow) {


        if (toFollow instanceof WorldItem){

            WorldItem item = (WorldItem) toFollow;
            if (item.getInventoryItem().getName().equals("Apple")){

                this.toFollow = toFollow;
            }
        }

        npc.setState(npc.getSearchingFoodState(), world);
    }

    @Override
    public void runFromEntity(World world, NPC npc, Entity toRunFrom) {
        npc.setState(npc.getAfraidState(), world);
        npc.getState().runFromEntity(world, npc, toRunFrom);
    }

    @Override
    public void onEnter(World world, NPC npc) {
        if (toFollow == null){
            //npc.setState(npc.getCalmState(), world);
            return;
        }



        npc.setHungerPoints(npc.getHungerPoints() + 1);
        npc.setEnergyPoints(npc.getEnergyPoints() - 1);

        int moveXInt, moveYInt;
        double base = world.getBlock(npc.getCenterPosition()).getWalkAction().getBaseWalkingSpeed();
        Position vector = toFollow.getPosition().difference(npc.getPosition());
        double dist = Position.dist(npc.getPosition(), toFollow.getPosition());

        if (dist < 10){
            world.removeItem(npc.getCenterPosition().getBlockPosition());
            npc.setHungerPoints(0);
            npc.setState(npc.getCalmState(), world);
            toFollow = null;
            return;
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
