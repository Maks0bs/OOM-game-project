package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.DamageWallAction;
import com.oom.game.main.environment.utils.Block;


public class BrickWall extends Block {
    public static final int DEFAULT_TEXTURE = 4;
    public BrickWall(){
        super(DEFAULT_TEXTURE);
        this.damageAction = new DamageWallAction();
    }
}
