package com.oom.game.main.process.utils;

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

    public PlayerControl(MainRenderable mainRenderable, World world){

        this.player = world.getPlayer();
        this.world = world;
        mainRenderable.getObservable().registerObserver(this);
        for (int i = 0; i < CONTROL_KEYS.length; i++){
            pressedDistance.put(CONTROL_KEYS[i], 0d);
        }
    }

    public void enable(){
        enabled = true;
    }

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

        double base = blockUnder.getWalkAction().getBaseWalkingSpeed();

        for (int i = 0; i < CONTROL_KEYS.length; i++){
            Character c = CONTROL_KEYS[i];
            if (newData.keyIsPressed(c)){
                double newValue = pressedDistance.get(c) + base;
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

    }
}
