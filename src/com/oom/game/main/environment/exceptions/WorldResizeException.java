package com.oom.game.main.environment.exceptions;

public class WorldResizeException extends Exception {
    /**
     * This exception is meant to be triggered when illegal world resizing occurs.
     * Is a checked exception and should be handled by the caller
     *
     * @param message message to be displayed as reason of exception
     */
    public WorldResizeException(String message){
        super(message);
    }
}
