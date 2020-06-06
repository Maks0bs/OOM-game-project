package com.oom.game.main.entities.interaction;

import com.oom.game.main.entities.Creature;

import java.util.HashSet;
import java.util.Set;


public interface FearBehaviour {
    /**
     *
     * @return set of creature types that the current one is afraid of
     */
    Set<String> getAfraidOfSet();

    /**
     * @param creature the creature to test on fear
     * @return true if the current creature is afraid of the mentioned one
     */
    boolean isAfraidOf(Creature creature);

    /**
     *
     * @return the radius (in px) in which the creatures notice others and start being afraid of them
     */
    int getNoticeRadius();
}
