package com.oom.game.main.environment.utils;

/**
 * TODO define default speed/distance/time measurements in game (like ticks and pixels im Minecraft)
 */

public interface WalkAction {
    int DEFAULT_WALKING_SPEED = 1;
    /**
     *
     * @return walking speed in pixels/tick
     */
    int getBaseWalkingSpeed();

    /**
     *
     * @return true if creatures can walk on this block, false otherwise
     */
    boolean canWalk();
    /**
     * Action that should be performed when a block gets walked on
     */
    void execute();
}
