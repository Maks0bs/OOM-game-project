package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.FloorWalkAction;
import com.oom.game.main.environment.utils.Block;

public class StoneTileFloor extends Block {
    public static final String DEFAULT_STATE = "StoneTileFloor";
    public StoneTileFloor(){
        super(DEFAULT_STATE);
        this.walkAction = new FloorWalkAction(0.5);
    }
}
