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
public abstract class CreatureBehaviour implements Serializable {
    private boolean enabled = false;
    protected double speed = 1d;

    /**
     * @param speed base movement speed of player in the given world
     */
    public CreatureBehaviour(double speed){
        this.speed = speed;
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

    /**
     * {@link #executeUpdate(World, Creature)}
     * executeUpdate only gets called when movement is enabled
     */
    public boolean update(World world, Creature creature){
        // Don't let updating when movement is disabled
        if (!enabled){
            return false;
        }

        // I really like this pattern for hiding functionality

        return executeUpdate(world, creature);
    }

    /**
     *
     * @param world world data to determine how to move
     * @param creature creature that has to be moved
     * @return true if movement was successful
     */
    protected abstract boolean executeUpdate(World world, Creature creature);
}
