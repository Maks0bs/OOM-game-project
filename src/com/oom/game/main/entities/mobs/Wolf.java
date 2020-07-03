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

    /**
     * see constructor of {@link NPC}
     */
    public Wolf(Position position){
        super(NAME, position, SIZE_X, SIZE_Y, DEFAULT_STATE, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        super.aggresiveBehaviour = new Level1Aggression();
    }

    /*
        FIXME add a constructor to create a rabbit with custom stats
     */

    @Override
    protected NPCState getIdleState() {
        return new IdleState(2);
    }

    @Override
    protected NPCState getSearchingFoodState() {
        return new SearchingFoodState(1.5);
    }

    @Override
    protected NPCState getSleepinState() {
        return new SleepingState(0);
    }

    @Override
    protected NPCState getAggressiveState() {
        return new AggressiveState(2);
    }

    @Override
    protected NPCState getAfraidState() {
        return new AfraidState(2.5);
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
