package com.oom.game.main.process.utils.control;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.environment.World;

public class NoneBehaviour extends CreatureBehaviour {

    public NoneBehaviour(){
        super(0d);
    }

    @Override
    protected boolean executeUpdate(World world, Creature creature) {
        return false;
    }
}
