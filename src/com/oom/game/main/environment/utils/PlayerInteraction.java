package com.oom.game.main.environment.utils;

import java.io.Serializable;

public interface PlayerInteraction extends Serializable {
    /**
     * action that should be performed when user clicks on block as a player
     */
    void onClick();
}
