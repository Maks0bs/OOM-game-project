package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.OpenBottom;
import com.oom.game.main.environment.utils.OpenTop;

public class Barrel extends Block implements OpenBottom, OpenTop {
    private Block blockBelow = null, blockOnTop = null;

    /**
     *
     * @param texture {@link Block}
     * @param blockBelow {@link OpenBottom}
     */
    Barrel(int texture, Block blockBelow){
        super(texture);
        this.blockBelow = blockBelow;
    }

    /**
     * {@link OpenBottom}
     * @return block this one sits on
     */
    @Override
    public Block getBlockBelow() {
        return blockBelow;
    }

    /**
     * {@link OpenTop}
     * @return block above this one
     */
    @Override
    public Block getBlockOnTop() {
        return blockBelow;
    }

    /**
     * {@link OpenTop}
     * @param block block to add on top
     */
    @Override
    public void addBlockOnTop(Block block) {
        if (blockOnTop == null){
            blockOnTop = block;
        }
    }
}
