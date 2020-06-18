package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.render.MainRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class made for automating results of pressing keys and other actions to control player
 */
public class PlayerControl extends CreatureMovement  {
    public static final class DIRECTIONS {
        public static final String UP = "U";
        public static final String RIGHT = "R";
        public static final String DOWN = "D";
        public static final String LEFT = "L";
        public static final String[] LIST = new String[]{UP, RIGHT, DOWN, LEFT};
    }
    private HashMap<String, Double> pressedDistance = new HashMap<>();
    private Map<String, Boolean> activeDirections = new HashMap<>();

    /**
     * @param speed base movement speed of player in the given world
     */
    public PlayerControl(double speed){
        super(speed);

        for (int i = 0; i < DIRECTIONS.LIST.length; i++){
            pressedDistance.put(DIRECTIONS.LIST[i], 0d);
            activeDirections.put(DIRECTIONS.LIST[i], false);
        }
    }

    /**
     * Lets the character move in given direction
     * @param direction the direction that is now available
     */
    public void activateDirection(String direction){
        activeDirections.replace(direction, true);
    }


    /**
     * Disables the character to move in given direction
     * @param direction the direction that is now unavailable
     */
    public void disableDirection(String direction){
        activeDirections.replace(direction, false);
    }

    @Override
    protected boolean executeUpdate(World world, Creature creature) {
        Player player = (Player) creature;

        Position blockPos = new Position(
                player.getPosition().getX() + (player.getSizeX() / 2),
                player.getPosition().getY() + (player.getSizeY() / 2)
        );
        Block blockUnder = world.getBlock(blockPos);

        if (!blockUnder.getWalkAction().canWalk()){
            return false;
        }
        double base = blockUnder.getWalkAction().getBaseWalkingSpeed();

        Position prevPos = new Position(player.getPosition());

        for (int i = 0; i < DIRECTIONS.LIST.length; i++){
            String c = DIRECTIONS.LIST[i];
            if (activeDirections.get(c)){
                movePlayer(player, c, base * speed);

                //FIXME perform action on walk
            }
        }


        //FIXME this approach disables walking into an unwalkable and walking in other direction (i know what i mean here)

        //Here we check if we walked on a non-walkable block and cancel this move if it happened
        Position blockPosNew = new Position(
                player.getPosition().getX() + (player.getSizeX() / 2),
                player.getPosition().getY() + (player.getSizeY() / 2)
        );
        Block blockUnderNew = world.getBlock(blockPosNew);
        if (!blockUnderNew.getWalkAction().canWalk()){
            player.setPositionDeep(prevPos);
            return false;
        }

        return true;
    }

    /**
     * move the player down
     * @param dist the distance to move the player on
     */
    public void movePlayer(Player player, String dir, double dist){
        double newValue = pressedDistance.get(dir) + dist;
        int diff = (int)(newValue);
        pressedDistance.replace(dir, newValue - diff);

        switch (dir){
            case DIRECTIONS.UP:
                player.move(0, -diff);
                break;
            case DIRECTIONS.LEFT:
                player.move(-diff, 0);
                break;
            case DIRECTIONS.DOWN:
                player.move(0,diff);
                break;
            case DIRECTIONS.RIGHT:
                player.move(diff, 0);
                break;
        }
    }
}
