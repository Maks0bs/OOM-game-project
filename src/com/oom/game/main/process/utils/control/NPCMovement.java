package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.NPC;
import com.oom.game.main.environment.World;
import com.oom.game.main.process.render.WorldRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

/**
 * Always create Movement for every entity you want to move somehow and activate this control in the appropriate Process
 * Fear always overrides aggressive behaviour, i. e. if a creature is afraid of another in the radius and
 * aggressive against another one in the radius, than it will run away from the one it's afraid of
 * in the first place
 */
public class NPCMovement implements GameObserver<WorldRenderable> {
    public static final Character CONTROL_KEYS [] = new Character[]{'w', 'a', 's', 'd'};
    private boolean enabled = false;
    private World world = null;
    private NPC creature;

    public NPCMovement(NPC creature, World world, WorldRenderable worldRenderable){
        this.creature = creature;
        this.world = world;
        worldRenderable.getObservable().registerObserver(this);
    }

    @Override
    public void update(GameObservable<WorldRenderable> observable, WorldRenderable newData) {

    }

    @Override
    public void update(GameObservable<WorldRenderable> observable, WorldRenderable newData, Object specs) {
        if (specs instanceof NPC){
            NPC c = (NPC) specs;
            if (c == creature){
                //FIXME maybe replace with .equals()
                //FIXME check if in radius of fear or aggression...

                //tmp
                creature.move(1, 1);
            }
        }
    }
}
