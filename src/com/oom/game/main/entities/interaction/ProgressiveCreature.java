package com.oom.game.main.entities.interaction;


import java.io.Serializable;

public interface ProgressiveCreature extends Serializable {
    /**
     *
     * @param exp amount of experience points to be added
     */
    void addExpPoints(int exp);

    /**
     *
     * @return current level of player
     */
    int getLevel();

    /**
     * action that should be performed when the player gets a new level
     * @param level current level of player
     */
    void onLevelUp(int level);

    /**
     * Function to display current players progress (mainly for console output)
     */
    void displayProgress();
}
