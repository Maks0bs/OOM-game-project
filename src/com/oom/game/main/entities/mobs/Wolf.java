package com.oom.game.main.entities.mobs;

import com.oom.game.main.entities.NPC;
import com.oom.game.main.entities.mobs.strategies.Level1Aggression;
import com.oom.game.main.entities.state.*;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

public class Wolf extends NPC {
    public static final String NAME = "Wolf", TYPE = "Wolf", DEFAULT_STATE = "Wolf";
    public static final int SIZE_X = (3 * World.BLOCK_SIZE / 2), SIZE_Y = World.BLOCK_SIZE,
            HEALTH_POINTS = 25, ATTACK_POINTS = 8, EXP_POINTS = 20;

    private static final NPCState CALM_STATE = new CalmState(1);
    private static final NPCState SEARCHING_FOOD_STATE = new SearchingFoodState(1);
    private static final NPCState SLEEPING_STATE = new SleepingState(0);
    private static final NPCState AGGRESSIVE_STATE = new AggressiveState(1.4);
    private static final NPCState AFRAID_STATE = new AfraidState(1.6);

    /**
     * see constructor of {@link NPC}
     */
    public Wolf(Position position){
        super(NAME, position, SIZE_X, SIZE_Y, DEFAULT_STATE, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        super.aggresiveBehaviour = new Level1Aggression();
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
     * @return basic info of wolf
     */
    @Override
    public String getInfo() {
        return
            this.name + "\n" +
            "HP: " + this.getHealthPoints() + "\n" +
            "ATK: " + this.getAttackPoints() + "\n";
    }
}
