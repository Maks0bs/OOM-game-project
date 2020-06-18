package com.oom.game.main.entities.interaction;

import com.oom.game.main.entities.Creature;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public interface AggressiveBehaviour extends Serializable {
    /**
     *
     * @return set of creature types that the current one is aggressive against
     */
    Set<String> getAggressiveAgainstSet();

    /**
     * @param creature the creature to test on aggression
     * @return true if the current creature is aggressive against the mentioned one
     */
    boolean isAggressiveAgainst(Creature creature);

    /**
     *
     * @return the radius (in px) in which the creatures notice others and can get aggressive
     */
    int getNoticeRadius();
}
