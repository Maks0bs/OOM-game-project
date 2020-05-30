package com.oom.game.main.environment.blocks.strategies;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.SolidBlockAction;

public class SolidBlockWallAction implements SolidBlockAction {
    private Position relativeSolidPosition;
    private int relativeSizeX, relativeSizeY;

    /**
     * Default constructor
     */
    public SolidBlockWallAction(){
        relativeSolidPosition = new Position(0, 0);
        relativeSizeX = World.BLOCK_SIZE;
        relativeSizeY = World.BLOCK_SIZE;
    }


    /**
     * {@link SolidBlockAction}
     */
    @Override
    public boolean isSolid() {
        return true;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public Position getRelativeSolidPosition() {
        return relativeSolidPosition;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public int getRelativeSolidSizeX() {
        return relativeSizeX;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public int getRelativeSolidSizeY() {
        return relativeSizeY;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public boolean isBreakable() {
        return false;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public int getBlockHealthPoints() {
        return 0;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public void onDamage() {

    }
}
