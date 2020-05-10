package com.oom.game.main.environment.utils;

public interface Movable {

    //FIXME add this interface to UML
    /**
     *
     * @return value that defines, how fast a creature can move this block
     */
    int getMovingSpeed();

    /**
     *
     * TODO currently power is not implemented for players/creatures. Add this to make this function
     * @return minimum amount of power required to move this block
     */
    int getPowerRestriction();
}
