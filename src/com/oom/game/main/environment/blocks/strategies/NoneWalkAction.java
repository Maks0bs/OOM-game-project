package com.oom.game.main.environment.blocks.strategies;

import com.oom.game.main.environment.utils.WalkAction;

public class NoneWalkAction implements WalkAction {

    /**
     * {@link WalkAction}
     */
    @Override
    public double getBaseWalkingSpeed() {
        return 0;
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public boolean canWalk() {
        return false;
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public void onWalk() {

    }
}
