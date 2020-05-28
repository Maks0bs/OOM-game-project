package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.ShowInventoryAction;
import com.oom.game.main.environment.utils.Block;

public class Barrel extends Block {
    public static final String DEFAULT_STATE = "Barrel";
    public Barrel(){
        super(DEFAULT_STATE);
        super.playerInteraction = new ShowInventoryAction();
    }
}
