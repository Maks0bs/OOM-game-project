package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.items.utils.InventoryItem;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;

/**
 * This class is a kind of state manager for creatures. It performs various actions
 */
public class DefaultNPCBehaviour extends CreatureBehaviour {
    private Entity toFollow = null, toRunFrom = null, nearestFood = null;
    private int optimizationCounter = 0;
    private double defaultMotionAngle = 0;
    private double moveX = 0, moveY = 0;

    public DefaultNPCBehaviour(double speed){
        super(speed);
    }

    @Override
    protected boolean executeUpdate(World world, Creature creature) {
        if (!(creature instanceof NPC)){
            return false;
        }
        NPC npc = (NPC) creature;
        Position blockPos = new Position(
                npc.getPosition().getX() + (npc.getSizeX() / 2),
                npc.getPosition().getY() + (npc.getSizeY() / 2)
        );
        Block blockUnder = world.getBlock(blockPos);

        if (!blockUnder.getWalkAction().canWalk()){
            return false;
        }

        optimizationCounter++;
        if (optimizationCounter > 30) {
            optimizationCounter = 0;

            Entity [] possibleAggressive = world.getBlocksInCenteredArea(blockPos,
                    npc.getAggresiveBehaviour().getNoticeRadius()
            );

            toFollow = Entity.DUMMY;

            //TODO make find nearest, not random
            for (int i = 0; i < possibleAggressive.length; i++){
                if (possibleAggressive[i] == creature){
                    continue;
                }
                if (possibleAggressive[i] instanceof Creature){
                    Creature cur = (Creature) possibleAggressive[i];
                    if (npc.getAggresiveBehaviour().isAggressiveAgainst(cur)){
                        toFollow = cur;
                        break;
                    }
                }
            }

            Entity [] possibleAfraid = world.getBlocksInCenteredArea(blockPos,
                    npc.getFearBehaviour().getNoticeRadius()
            );

            toRunFrom = Entity.DUMMY;

            //TODO make find nearest, not random
            for (int i = 0; i < possibleAfraid.length; i++){
                if (possibleAfraid[i] == creature){
                    continue;
                }
                if (possibleAfraid[i] instanceof Creature){
                    Creature cur = (Creature) possibleAfraid[i];
                    if (npc.getFearBehaviour().isAfraidOf(cur)){
                        toRunFrom = cur;
                        break;
                    }
                }
            }

            double minDist = Double.MAX_VALUE;
            nearestFood = Entity.DUMMY;

            for (int i = 0; i < world.getEntities().size(); i++){
                Entity cur = world.getEntities().get(i);
                if (cur instanceof WorldItem){
                    WorldItem item = (WorldItem) cur;
                    if (item.getInventoryItem().getName().equals("Apple")){
                        double dist = Position.dist(
                                npc.getPosition(),
                                item.getPosition()
                        );
                        if (dist < minDist){
                            minDist = dist;
                            nearestFood = cur;
                        }
                    }
                }
            }
        }

        if (toRunFrom != Entity.DUMMY){
            npc.getState().runFromEntity(world, npc, toRunFrom);
        }

        if (nearestFood != Entity.DUMMY){
            npc.getState().followEntity(world, npc, nearestFood);
        }

        if (toFollow != Entity.DUMMY){
            npc.getState().followEntity(world, npc, toFollow);
        }

        if (toFollow == Entity.DUMMY && toRunFrom == Entity.DUMMY){
            npc.getState().stayIdle(world, npc);
        }



        return true;
    }

}
