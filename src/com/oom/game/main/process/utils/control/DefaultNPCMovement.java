package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.render.MainRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

/**
 * Always create Movement for every entity you want to move somehow and activate this control in the appropriate Process
 * Fear always overrides aggressive behaviour, i. e. if a creature is afraid of another in the radius and
 * aggressive against another one in the radius, than it will run away from the one it's afraid of
 * in the first place
 */
public class DefaultNPCMovement extends CreatureMovement {
    private Creature angryOn = null;
    private int optimizationCounter = 0;
    private double defaultMotionAngle = 0;
    private double moveX = 0, moveY = 0;

    public DefaultNPCMovement(double speed){
        super(speed);
    }

    @Override
    protected boolean executeUpdate(World world, Creature creature) {
        NPC npc = (NPC) creature;
        Position blockPos = new Position(
                npc.getPosition().getX() + (npc.getSizeX() / 2),
                npc.getPosition().getY() + (npc.getSizeY() / 2)
        );
        Block blockUnder = world.getBlock(blockPos);

        if (!blockUnder.getWalkAction().canWalk()){
            return false;
        }
        double base = blockUnder.getWalkAction().getBaseWalkingSpeed();

        optimizationCounter++;
        if (optimizationCounter > 25) {
            optimizationCounter = 0;

            boolean foundAngry = false;
            for (int i = -1; i < world.getEntities().size(); i++) {
                if (i >= 0 && !(world.getEntities().get(i) instanceof Creature)) {
                    continue;
                }
                Creature cur;
                if (i == -1) {
                    cur = world.getPlayer();
                } else {
                    cur = (Creature) world.getEntities().get(i);
                }

                double dist = Position.dist(npc.getPosition(), cur.getPosition());

                if (dist < npc.getAggresiveBehaviour().getNoticeRadius() * World.BLOCK_SIZE &&
                        npc.getAggresiveBehaviour().isAggressiveAgainst(cur)
                ) {
                    angryOn = cur;
                    foundAngry = true;

                    break;
                }
            }
            if (!foundAngry) {
                angryOn = null;
            }
        }



        if (angryOn != null){
            Position vector = angryOn.getPosition().difference(npc.getPosition());
            double dist = Position.dist(npc.getPosition(), angryOn.getPosition());

            if (dist < 0.0000001){
                return false;
            }

            double vectorX = vector.getX() / dist;
            double vectorY = vector.getY() / dist;

            if (Double.isNaN(vectorX) || Double.isNaN(vectorY)){
                return false;
            }

            moveX += vectorX * speed * base;
            moveY += vectorY * speed * base;

            int moveXInt = (int) moveX;
            int moveYInt = (int) moveY;

            moveX -= moveXInt;
            moveY -= moveYInt;

            npc.move(moveXInt, moveYInt);
        }
        else {

            //Simply keep moving on a circle if not angry

            moveX += Math.cos(defaultMotionAngle) * speed * base;
            moveY += Math.sin(defaultMotionAngle) * speed * base;

            int moveXInt = (int) moveX;
            int moveYInt = (int) moveY;

            moveX -= moveXInt;
            moveY -= moveYInt;

            npc.move(moveXInt, moveYInt);

            defaultMotionAngle += Math.PI / 36;
            if (defaultMotionAngle > 2 * Math.PI) {
                defaultMotionAngle -= 2 * Math.PI;
            }
        }

        Position prevPos = new Position(npc.getPosition());



        //FIXME this approach disables walking into an unwalkable and walking in other direction (i know what i mean here)

        //Here we check if we walked on a non-walkable block and cancel this move if it happened
        Position blockPosNew = new Position(
                npc.getPosition().getX() + (npc.getSizeX() / 2),
                npc.getPosition().getY() + (npc.getSizeY() / 2)
        );
        Block blockUnderNew = world.getBlock(blockPosNew);
        if (!blockUnderNew.getWalkAction().canWalk()){
            npc.setPositionDeep(prevPos);
            //defaultMotionAngle += Math.PI / 180;
            return false;
        }

        return true;
    }

}
