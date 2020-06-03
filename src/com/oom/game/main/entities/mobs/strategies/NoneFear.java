package com.oom.game.main.entities.mobs.strategies;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.interaction.FearBehaviour;

import java.util.Set;

public class NoneFear implements FearBehaviour {
    @Override
    public Set<String> getAfraidOfSet() {
        return null;
    }

    @Override
    public boolean isAfraidOf(Creature creature) {
        return false;
    }

    @Override
    public int getNoticeRadius() {
        return 0;
    }
}
