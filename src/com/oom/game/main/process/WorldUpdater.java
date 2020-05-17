package com.oom.game.main.process;

import com.oom.game.main.environment.World;
import gameCore.IRenderable;
import gameCore.IUpdatable;

import java.util.ArrayList;

public class WorldUpdater implements IUpdatable {
    /*
        FIXME add this class to UML
     */

    private WorldRenderer renderer;

    /**
     * @param renderer world renderer for this updater
     */
    public WorldUpdater(WorldRenderer renderer){
        this.renderer = renderer;
    }

    /**
     * {@link IUpdatable}
     */
    @Override
    public void update(long elapsedMillis) {
        renderer.render();
    }


}
