package com.oom.game.main.environment.utils.exceptions;

import com.oom.game.main.environment.Position;

public class PositionOutOfWorldException extends Exception {

    /**
     * This exception is meant to restrict addressing illegal positions outside of the
     * target world to prohibit null pointers and memory problems.
     * Should be used only in CRITICAL world addressing situations
     * @param message message to be displayed as reason of exception
     */
    public PositionOutOfWorldException(String message){
        super(message);
    }

    public PositionOutOfWorldException(){
        super("Position out of world bounds");
    }
}
