package com.oom.game.main.environment.blocks.strategies;

import com.oom.game.main.environment.utils.WalkAction;

public class FloorWalkAction implements WalkAction {
    private double step = WalkAction.DEFAULT_WALKING_STEP;

    /**
     * Default constructor
     */
    public FloorWalkAction(){
    }

    /**
     *
     * @param step custom base step
     */
    public FloorWalkAction(double step){
        this.step = step;
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public double getBaseWalkingSpeed() {
        return step;
    }

    /**
     * {@link WalkAction}
     */
    @Override
    public boolean canWalk() {
        return true;
    }
}
