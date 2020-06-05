package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.render.WorldRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

import java.util.HashMap;

/**
 * Always create Movement for every entity you want to move somehow and activate this control in the appropriate Process
 * Fear always overrides aggressive behaviour, i. e. if a creature is afraid of another in the radius and
 * aggressive against another one in the radius, than it will run away from the one it's afraid of
 * in the first place
 */
public class NPCMovement implements GameObserver<WorldRenderable> {
    public static final class INTERACTION_UPDATES {
        public static final int VICTIM_IN_SIGHT = -1;
    }
    public static final Character CONTROL_KEYS [] = new Character[]{'w', 'a', 's', 'd'};
    private boolean enabled = false;
    private double speed = 0;
    private Creature angryOn = null;
    private int optimizationCounter = 0;
    private double defaultMotionAngle = 0;
    //private HashMap<String, Double> directions = new HashMap<>();
    private double moveX = 0, moveY = 0;
    private World world = null;
    private NPC creature;

    public NPCMovement(NPC creature, World world, WorldRenderable worldRenderable, double speed){
        this.creature = creature;
        this.world = world;
        this.speed = speed;
        worldRenderable.getObservable().registerObserver(this);
//        directions.put("down", 0d);
//        directions.put("up", 0d);
//        directions.put("left", 0d);
//        directions.put("right", 0d);

    }

    public void enable(){
        enabled = true;
    }

    public void disable(){
        enabled = false;
    }

    @Override
    public void update(GameObservable<WorldRenderable> observable, WorldRenderable newData) {
        if (!enabled){
            return;
        }

        Position blockPos = new Position(
                creature.getPosition().getX() + (creature.getSizeX() / 2),
                creature.getPosition().getY() + (creature.getSizeY() / 2)
        );
        Block blockUnder = world.getBlock(blockPos);

        if (!blockUnder.getWalkAction().canWalk()){
            return;
        }
        double base = blockUnder.getWalkAction().getBaseWalkingSpeed();

        optimizationCounter++;
        if (optimizationCounter > 100) {
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

                double dist = Position.dist(creature.getPosition(), cur.getPosition());

                if (dist < creature.getAggresiveBehaviour().getNoticeRadius() * World.BLOCK_SIZE &&
                        creature.getAggresiveBehaviour().isAggressiveAgainst(cur)
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
            Position vector = angryOn.getPosition().difference(creature.getPosition());
            double dist = Position.dist(creature.getPosition(), angryOn.getPosition());

            if (dist < 0.0000001){
                return;
            }

            double vectorX = vector.getX() / dist;
            double vectorY = vector.getY() / dist;

            if (Double.isNaN(vectorX) || Double.isNaN(vectorY)){
                return;
            }

            moveX += vectorX * speed * base;
            moveY += vectorY * speed * base;

            int moveXInt = (int) moveX;
            int moveYInt = (int) moveY;

            moveX -= moveXInt;
            moveY -= moveYInt;

            creature.move(moveXInt, moveYInt);
        }
        else {

            //Simply keep moving on a circle if not angry

            moveX += Math.cos(defaultMotionAngle) * speed * base;
            moveY += Math.sin(defaultMotionAngle) * speed * base;

            int moveXInt = (int) moveX;
            int moveYInt = (int) moveY;

            moveX -= moveXInt;
            moveY -= moveYInt;

            creature.move(moveXInt, moveYInt);

            defaultMotionAngle += Math.PI / 180;
            if (defaultMotionAngle > 2 * Math.PI) {
                defaultMotionAngle -= 2 * Math.PI;
            }
        }

        Position prevPos = new Position(creature.getPosition());



        //FIXME this approach disables walking into an unwalkable and walking in other direction (i know what i mean here)

        //Here we check if we walked on a non-walkable block and cancel this move if it happened
        Position blockPosNew = new Position(
                creature.getPosition().getX() + (creature.getSizeX() / 2),
                creature.getPosition().getY() + (creature.getSizeY() / 2)
        );
        Block blockUnderNew = world.getBlock(blockPosNew);
        if (!blockUnderNew.getWalkAction().canWalk()){
            creature.setPositionDeep(prevPos);
            //defaultMotionAngle += Math.PI / 180;
        }
    }
}
