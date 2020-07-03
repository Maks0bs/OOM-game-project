package com.oom.game.main.entities.state;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.World;

public interface NPCState {

    /**
     * Performed, when no events occur
     * @param world world the creature is currently in
     * @param npc the creature to perform action on
     */
    void stayIdle(World world, NPC npc);

    /**
     *
     * @param world world the creature is currently in
     * @param npc the creature that follows the entity
     * @param toFollow the entity that is followed
     */
    void followEntity(World world, NPC npc, Entity toFollow);

    /**
     * @param world world the creature is currently in
     * @param npc the creature that runs away from the entity
     * @param toRunFrom entity to run away from
     */
    void runFromEntity(World world, NPC npc, Entity toRunFrom);

    /**
     * @param world world the creature is currently in
     * @param npc the creature to perform action on
     */
    void onEnter(World world, NPC npc);

    /**
     * @param world world the creature is currently in
     * @param npc the creature to perform action on
     */
    void onExit(World world, NPC npc);

    /**
     *
     * @return
     */
    int getSpeed();
}
