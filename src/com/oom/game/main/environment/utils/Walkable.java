package com.oom.game.main.environment.utils;

/**
 * TODO define default speed/distance/time measurements in game (like ticks and pixels im Minecraft)
 */

public interface Walkable {
    //FIXME add this interface to UML
    int DEFAULT_WALKING_SPEED = 1;

    /**
     *
     * @return walking speed in pixels/tick
     */
    int getBaseWalkingSpeed();
    // void onWalk(); = action when creature walks on top of this block
}
