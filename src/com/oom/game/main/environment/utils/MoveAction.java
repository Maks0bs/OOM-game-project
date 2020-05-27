package com.oom.game.main.environment.utils;

public interface MoveAction {
    /**
     * Action that should be performed when a block gets moved
     */
    void onMove();

    /**
     *
     * @return true if block can be dynamically moved, false otherwise
     */
    boolean isMovable();

    /**
     * @return the amount of in-game steps required to move 1 pixel of block, like in {@link WalkAction}
     */
    double getMoveEffort();
}
