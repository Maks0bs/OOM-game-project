package com.oom.game.main.environment.utils;

public interface Stackable {

    //FIXME add this interface to UML
    /**
     *
     * @return the block that this one sits on
     */
    Block getBlockBelow();

    /**
     * can be considered to be a z-coordinate in this game!!!
     * @return the amount of block that are below this one
     */
    int getBelowBlocksAmount();
}
