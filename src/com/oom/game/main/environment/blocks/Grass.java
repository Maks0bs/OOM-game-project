package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.FloorWalkAction;
import com.oom.game.main.environment.utils.Block;

/*
    Notice that grass block is not stackable. This means that it can only be used as floor (z-coord = 0), which makes sense
    Grass can't grow on any other blocks.
    But you can put other blocks on it
 */
public class Grass extends Block {
    public static final String DEFAULT_TEXTURE = "res/blocks/32px/Grass.png";
    public Grass(){
        super(DEFAULT_TEXTURE);
        this.walkAction = new FloorWalkAction();
    }
}
