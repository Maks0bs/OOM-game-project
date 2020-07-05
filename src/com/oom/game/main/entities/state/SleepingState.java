package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.World;

public class SleepingState implements NPCState {
    private double speed = 1d;

    public SleepingState(double speed){
        this.speed = speed;
    }

    private void sleep(World world, NPC npc){
        if (npc.getEnergyPoints() >= 500){
            npc.setState(npc.getCalmState(), world);
        } else {
            npc.setState(npc.getSleepingState(), world);
        }
    }

    @Override
    public void stayIdle(World world, NPC npc) {
        sleep(world, npc);
    }

    @Override
    public void followEntity(World world, NPC npc, Entity toFollow) {
        sleep(world, npc);
    }

    @Override
    public void runFromEntity(World world, NPC npc, Entity toRunFrom) {
        sleep(world, npc);
    }

    @Override
    public void onEnter(World world, NPC npc) {
        npc.setEnergyPoints(npc.getEnergyPoints() + 2);
    }

    @Override
    public void onExit(World world, NPC npc) {

    }

    @Override
    public double getSpeed() {
        return speed;
    }
}
