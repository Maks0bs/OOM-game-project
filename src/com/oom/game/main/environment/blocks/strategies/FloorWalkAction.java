package com.oom.game.main.environment.blocks.strategies;

import com.oom.game.main.environment.utils.WalkAction;

public class FloorWalkAction implements WalkAction {
    public FloorWalkAction(){
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public int getBaseWalkingSpeed() {
        return 0;
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public boolean canWalk() {
        return true;
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public void execute() {
        //walk on floor
    }
}
