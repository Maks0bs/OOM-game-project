package com.oom.game.main.entities.mobs.strategies;

import com.oom.game.main.entities.interaction.AggresiveBehaviour;

import java.util.HashSet;

public class Level1Aggresion implements AggresiveBehaviour {
    /*
        FIXME add this class to UML
     */
    public Level1Aggresion(){
        //aggressive against peaceful mobs
    }

    @Override
    public HashSet<String> getAggresiveAgainstSet() {
        return null;//rabbits, etc.
    }
}
