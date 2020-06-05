package com.oom.game.main.environment.blocks.strategies;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.utils.SolidBlockAction;

public class NoneSolidBlockAction implements SolidBlockAction {

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public boolean isSolid() {
        return false;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public Position getRelativeSolidPosition() {
        return null;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public int getRelativeSolidSizeX() {
        return 0;
    }

    /**
     * {@link SolidBlockAction}
     */
    @Override
    public int getRelativeSolidSizeY() {
        return 0;
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
