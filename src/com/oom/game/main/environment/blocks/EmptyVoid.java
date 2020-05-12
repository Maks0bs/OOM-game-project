package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.utils.Block;

/*
    EmptyVoid block should be used when we delete a certain block.
    This is just a placeholder.
    Intended to use instead of null pointer
 */

public class EmptyVoid extends Block {
    public static final int DEFAULT_TEXTURE = 1;
    public EmptyVoid(){
        super(DEFAULT_TEXTURE);
    }
}
