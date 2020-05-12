package com.oom.game.main.environment.blocks;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.OpenTop;
import com.oom.game.main.environment.utils.Walkable;

/*
    Notice that grass block is not stackable. This means that it can only be used as floor (z-coord = 0), which makes sense
    Grass can't grow on any other blocks.
    But you can put other blocks on it
 */
public class Grass extends Block implements Walkable, OpenTop {
    private Entity hiddenEntity; //might be redundant, added this member just for example (some item mey be hidden in the grass)
    /*
        this is DISGUSTING. Might need to create a separate class that implements OpenTop
        and should extend from that class. ATM it's not essential
     */
    private Block blockOnTop = null;

    /**
     * @param texture {@link Block}
     * @param hiddenEntity the entity (item in most cases) that is hidden in the grass. It gets revealed after you walk on the grass
     */
    Grass(int texture, Entity hiddenEntity){
        super(texture);
        this.hiddenEntity = hiddenEntity;
    }

    /**
     *
     * @param texture {@link Block}
     */
    Grass(int texture){
        super(texture);
    }

    /**
     *
     * {@link Walkable}
     */
    @Override
    public int getBaseWalkingSpeed() {
        return Walkable.DEFAULT_WALKING_SPEED;
    }

    /**
     * {@link OpenTop}
     */
    @Override
    public Block getBlockOnTop() {
        return blockOnTop;
    }

    /**
     * {@link OpenTop}
     * @param block block to add on top
     */
    @Override
    public void addBlockOnTop(Block block) {
        if (blockOnTop == null){
            blockOnTop = block;
        }
    }
}
