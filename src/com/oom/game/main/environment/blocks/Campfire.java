package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.ClickInteractable;
import com.oom.game.main.environment.utils.OpenBottom;

public class Campfire extends Block implements OpenBottom, ClickInteractable {
    private Block blockBelow = null;

    /**
     *
     * @param texture {@link Block}
     * @param blockBelow {@link OpenBottom}
     */
    Campfire(int texture, Block blockBelow){
        super(texture);
        this.blockBelow = blockBelow;
    }

    /**
     *
     * @return {@link OpenBottom}
     */
    @Override
    public Block getBlockBelow() {
        return blockBelow;
    }

    /**
     * {@link ClickInteractable}
     */
    @Override
    public void onClick() {

    }
}
