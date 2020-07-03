package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.World;

public class AfraidState implements NPCState {
    private double speed = 1d;

    public AfraidState(double speed){
        this.speed = speed;
    }

    @Override
    public void stayIdle(World world, NPC npc) {
//        int moveXInt, moveYInt;
//
//        if (angryOn != null){
//            Position vector = angryOn.getPosition().difference(npc.getPosition());
//            double dist = Position.dist(npc.getPosition(), angryOn.getPosition());
//
//            if (dist < 0.0000001){
//                return false;
//            }
//
//            double vectorX = vector.getX() / dist;
//            double vectorY = vector.getY() / dist;
//
//            if (Double.isNaN(vectorX) || Double.isNaN(vectorY)){
//                return false;
//            }
//
//            moveX += vectorX * speed * base;
//            moveY += vectorY * speed * base;
//
//            moveXInt = (int) moveX;
//            moveYInt = (int) moveY;
//
//            moveX -= moveXInt;
//            moveY -= moveYInt;
//        }
//        else {
//
//            //Simply keep moving on a circle if not angry
//
//            moveX += Math.cos(defaultMotionAngle) * speed * base;
//            moveY += Math.sin(defaultMotionAngle) * speed * base;
//
//            moveXInt = (int) moveX;
//            moveYInt = (int) moveY;
//
//            moveX -= moveXInt;
//            moveY -= moveYInt;
//
//
//
//            defaultMotionAngle += Math.PI / 36;
//            if (defaultMotionAngle > 2 * Math.PI) {
//                defaultMotionAngle -= 2 * Math.PI;
//            }
//        }
//
//        NPC.safelyMove(world, npc, moveXInt, moveYInt);
    }

    @Override
    public void followEntity(World world, NPC npc, Entity toFollow) {

    }

    @Override
    public void runFromEntity(World world, NPC npc, Entity toRunFrom) {

    }

    @Override
    public void onEnter(World world, NPC npc) {

    }

    @Override
    public void onExit(World world, NPC npc) {

    }

    @Override
    public int getSpeed() {
        return 0;
    }
}
