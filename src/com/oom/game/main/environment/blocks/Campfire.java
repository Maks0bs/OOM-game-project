package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.blocks.strategies.ShowInventoryAction;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.PlayerInteraction;

public class Campfire extends Block {
    public static final String DEFAULT_STATE = "";
    public Campfire(){
        super(DEFAULT_STATE);
        super.playerInteraction = new ShowInventoryAction();
    }

    // add cooking functionality
}
