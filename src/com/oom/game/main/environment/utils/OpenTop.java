package com.oom.game.main.environment.utils;

public interface OpenTop {

    //FIXME add this interface to UML
    /**
     *
     * @return the block that sits on top of this one
     */
    Block getBlockOnTop();

    /**
     * Function that allow you to add a block on top of the target OpenTop block
     */
    void addBlockOnTop(Block block);
}
