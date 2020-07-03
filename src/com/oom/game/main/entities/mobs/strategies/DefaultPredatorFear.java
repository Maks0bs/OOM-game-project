package com.oom.game.main.entities.mobs.strategies;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.interaction.FearBehaviour;

import java.util.HashSet;
import java.util.Set;

public class DefaultPredatorFear implements FearBehaviour {
    private Set<String> afraidOf = new HashSet<>();

    public DefaultPredatorFear(){
        afraidOf.add("Wolf");
    }

    @Override
    public Set<String> getAfraidOfSet() {
        return afraidOf;
    }

    @Override
    public boolean isAfraidOf(Creature creature) {
        return afraidOf.contains(creature.getName());
    }

    @Override
    public int getNoticeRadius() {
        return 3;
    }
}
