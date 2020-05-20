package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.FloorWalkAction;
import com.oom.game.main.environment.utils.Block;

public class Grass extends Block {
    public static final String DEFAULT_STATE = "Grass";
    public Grass(){
        super(DEFAULT_STATE);
        this.walkAction = new FloorWalkAction();
    }
}
