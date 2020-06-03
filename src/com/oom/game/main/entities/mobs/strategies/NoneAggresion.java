package com.oom.game.main.entities.mobs.strategies;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.interaction.AggressiveBehaviour;

import java.util.Set;

public class NoneAggresion implements AggressiveBehaviour {

    @Override
    public Set<String> getAggressiveAgainstSet() {
        return null;
    }

    @Override
    public boolean isAggressiveAgainst(Creature creature) {
        return false;
    }

    @Override
    public int getNoticeRadius() {
        return 0;
    }
}
