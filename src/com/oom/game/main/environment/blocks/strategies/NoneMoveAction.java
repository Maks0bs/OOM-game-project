package com.oom.game.main.environment.blocks.strategies;

import com.oom.game.main.environment.utils.MoveAction;

public class NoneMoveAction implements MoveAction {

    /**
     * {@link MoveAction}
     */
    @Override
    public void onMove() {

    }

    /**
     * {@link MoveAction}
     */
    @Override
    public boolean isMovable() {
        return false;
    }

    /**
     * {@link MoveAction}
     */
    @Override
    public double getMoveEffort() {
        return 0;
    }
}
