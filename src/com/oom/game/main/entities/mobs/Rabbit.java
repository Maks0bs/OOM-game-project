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

    /**
     * see constructor of {@link NPC}
     */
    public Rabbit(Position position){
        super(NAME, position, SIZE_X, SIZE_Y, DEFAULT_STATE, HEALTH_POINTS, ATTACK_POINTS, EXP_POINTS);
        super.fearBehaviour = new DefaultPredatorFear();
    }
    /*
        FIXME add a constructor to create a rabbit with custom stats
     */

    @Override
    protected NPCState getIdleState() {
        return new IdleState(1);
    }

    @Override
    protected NPCState getSearchingFoodState() {
        return new SearchingFoodState(1);
    }

    @Override
    protected NPCState getSleepinState() {
        return new SleepingState(0);
    }

    @Override
    protected NPCState getAggressiveState() {
        return new AggressiveState(1);
    }

    @Override
    protected NPCState getAfraidState() {
        return new AfraidState(3);
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
