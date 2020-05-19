package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.ShowInventoryAction;
import com.oom.game.main.environment.utils.Block;

public class Barrel extends Block {
    public static final String DEFAULT_TEXTURE = "res/blocks/32px/Barrel.png";
    public Barrel(){
        super(DEFAULT_TEXTURE);
        super.playerInteraction = new ShowInventoryAction();
    }
}
