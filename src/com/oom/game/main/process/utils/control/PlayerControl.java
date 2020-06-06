package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.render.MainRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

import java.util.HashMap;

/**
 * Class made for automating results of pressing keys and other actions to control player
 */
public class PlayerControl implements GameObserver<MainRenderable> {
    public static final Character CONTROL_KEYS [] = new Character[]{'w', 'a', 's', 'd'};
    private boolean enabled = false;
    private HashMap<Character, Double> pressedDistance = new HashMap<>();
    private World world = null;
    private Player player = null;
    private double speed = 1;

    /**
     *
     * @param mainRenderable renderable to read input from
     * @param world world to read data from
     * @param speed base movement speed of player in the given world
     */
    public PlayerControl(MainRenderable mainRenderable, World world, double speed){

        this.player = world.getPlayer();
        this.world = world;
        this.speed = speed;
        mainRenderable.getObservable().registerObserver(this);
        for (int i = 0; i < CONTROL_KEYS.length; i++){
            pressedDistance.put(CONTROL_KEYS[i], 0d);
        }
    }

    /**
     * Enables these controls
     */
    public void enable(){
        enabled = true;
    }

    /**
     * disables these controls
     */
    public void disable(){
        enabled = false;
    }


    @Override
    public void update(GameObservable<MainRenderable> observable, MainRenderable newData) {
        if(!enabled){
            return;
        }

        Position blockPos = new Position(
                player.getPosition().getX() + (player.getSizeX() / 2),
                player.getPosition().getY() + (player.getSizeY() / 2)
        );
        Block blockUnder = world.getBlock(blockPos);

        if (!blockUnder.getWalkAction().canWalk()){
            return;
        }
        double base = blockUnder.getWalkAction().getBaseWalkingSpeed();

        Position prevPos = new Position(player.getPosition());

        for (int i = 0; i < CONTROL_KEYS.length; i++){
            Character c = CONTROL_KEYS[i];
            if (newData.keyIsPressed(c)){
                double newValue = pressedDistance.get(c) + base * speed;
                int diff = (int)(newValue);
                pressedDistance.replace(c, newValue - diff);
                //FIXME don't move one time, make it in a loop and move one pixel at a time to make it look more smooth
                switch (c){
                    case 'w':
                        player.move(0, -diff);
                        break;
                    case 'a':
                        player.move(-diff, 0);
                        break;
                    case 's':
                        player.move(0,diff);
                        break;
                    case 'd':
                        player.move(diff, 0);
                        break;
                }

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
        }


    }
}
