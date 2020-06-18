package com.oom.game.main.utils.command;

import java.awt.*;

public interface GameCommand {
    /**
     * Executes the specified action
     */
    public void execute();

    /**
     * Optional action that undoes the one specified in execute
     */
    public default void undo(){
    }
}
