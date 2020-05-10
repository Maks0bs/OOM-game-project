package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.utils.Block;

/*
    Void block should be used when we delete a certain block.
    This is just a placeholder.
    Intended to use instead of null pointer
 */

public class Void extends Block {
    //FIXME implements some functionality here. For example, if creature walks on the void, they die.


    Void(int texture){
        super(texture);
    }
}
