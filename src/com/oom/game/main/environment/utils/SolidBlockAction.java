package com.oom.game.main.environment.utils;

/*
    Solid blocks cannot be used as floor. They can only be placed on top of others
 */

import com.oom.game.main.environment.Position;

import java.io.Serializable;

public interface SolidBlockAction extends Serializable {

    /**
     * If a block is solid, it means that entities cannot go through it. No entity can overlap with a solid block
     * @return true if block is solid
     */
    boolean isSolid();

    /**
     *
     * @return the position of the rectangle, with which the entities cannot collide
     */
    Position getRelativeSolidPosition();

    /**
     *
     * @return the width of the rectangle, with which the entities cannot collide
     */
    int getRelativeSolidSizeX();

    /**
     *
     * @return the height of the rectangle, with which the entities cannot collide
     */
    int getRelativeSolidSizeY();

    /**
     *
     * @return true if block can be destroyed with attacking, false otherwise
     */
    boolean isBreakable();

    /**
     *
     * @return the amount of health points for current solid block (if damaged the amount of HP gets decreased)
     */
    int getBlockHealthPoints();

    /**
     * Action that should be performed, when a block gets damaged
     */
    void onDamage();


}
