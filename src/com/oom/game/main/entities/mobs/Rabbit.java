package com.oom.game.main.entities.mobs;

/*
    Redundant separate class, should remove this and only use NPC class
 */

import com.oom.game.main.entities.NPC;
import com.oom.game.main.entities.mobs.strategies.DefaultPredatorFear;
import com.oom.game.main.entities.state.*;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

public class Rabbit extends NPC {
    public static final String NAME = "Rabbit", TYPE = "Rabbit", DEFAULT_STATE = "Rabbit";
    public static final int SIZE_X = World.BLOCK_SIZE / 4, SIZE_Y = World.BLOCK_SIZE / 4,
            HEALTH_POINTS = 4, ATTACK_POINTS = 1, EXP_POINTS = 2;

    private static final NPCState CALM_STATE = new CalmState(0.5);
    private static final NPCState SEARCHING_FOOD_STATE = new SearchingFoodState(1);
    private static final NPCState SLEEPING_STATE = new SleepingState(0);
    private static final NPCState AGGRESSIVE_STATE = new AggressiveState(1);
    private static final NPCState AFRAID_STATE = new AfraidState(2.1);

    /**
     * see constructor of {@link NPC}
     */
    public Rabbit(Position position){
        super(NAME, position, SIZE_X, SIZE_Y, DEFAULT_STATE, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        super.fearBehaviour = new DefaultPredatorFear();
        super.state = CALM_STATE;
    }
    /*
        FIXME add a constructor to create a rabbit with custom stats
     */

    @Override
    public NPCState getCalmState() {
        return CALM_STATE;
    }

    @Override
    public NPCState getSearchingFoodState() {
        return SEARCHING_FOOD_STATE;
    }

    @Override
    public NPCState getSleepingState() {
        return SLEEPING_STATE;
    }

    @Override
    public NPCState getAggressiveState() {
        return AGGRESSIVE_STATE;
    }

    @Override
    public NPCState getAfraidState() {
        return AFRAID_STATE;
    }

    /**
     * {@link com.oom.game.main.entities.Entity}
     * @return basic info of rabbit
     */
    @Override
    public String getInfo() {
        return
            this.name + "\n" +
            "HP: " + this.getHealthPoints() + "\n" +
            "ATK: " + this.getAttackPoints() + "\n";
    }
}
