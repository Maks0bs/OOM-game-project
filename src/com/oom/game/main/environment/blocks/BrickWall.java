package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.SolidBlockWallAction;
import com.oom.game.main.environment.utils.Block;


public class BrickWall extends Block {
    public static final String DEFAULT_STATE = "";
    public BrickWall(){
        super(DEFAULT_STATE);
        this.solidBlockAction = new SolidBlockWallAction();
    }
}
