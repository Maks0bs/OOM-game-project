package com.oom.game.main.gameCore;

public interface IUpdatable {

    /**
     * Gets called by an occurred update
     * @param elapsedMillis elapsed time from the last update im ms
     */
    public void update(long elapsedMillis);
}
