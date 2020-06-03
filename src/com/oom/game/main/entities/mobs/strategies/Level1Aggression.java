package com.oom.game.main.entities.mobs.strategies;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.interaction.AggressiveBehaviour;

import java.util.HashSet;
import java.util.Set;

public class Level1Aggression implements AggressiveBehaviour {
    Set<String> aggresiveAgainst = new HashSet<>();
    /*
        FIXME add this class to UML
     */
    public Level1Aggression(){
        aggresiveAgainst.add("Rabbit");
    }

    @Override
    public Set<String> getAggressiveAgainstSet() {
        return aggresiveAgainst;//rabbits, etc.
    }

    @Override
    public boolean isAggressiveAgainst(Creature creature) {
        return aggresiveAgainst.contains(creature.getName());
    }

    @Override
    public int getNoticeRadius() {
        return 5;
    }
}
