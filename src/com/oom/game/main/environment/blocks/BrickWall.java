package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.Destructable;
import com.oom.game.main.environment.utils.OpenBottom;


public class BrickWall extends Block implements Destructable, OpenBottom {
    //FIXME add this interface to UML
    public static final double DEFAULT_THICKNESS = 1.0;
    private double thickness;
    private Block blockBelow;

    /**
     *
     * @param texture {@link Block}
     * @param thickness thickness of the block (may be used to define when it can be destroyed)
     * @param blockBelow block below the wall
     */
    BrickWall(int texture, double thickness, Block blockBelow){
        super(texture);
        this.thickness = thickness;
        this.blockBelow = blockBelow;
    }

    /**
     * {@link OpenBottom}
     * @return block the wall sits on
     */
    @Override
    public Block getBlockBelow() {
        return blockBelow;
    }

    /**
     * {@link Destructable}
     */
    @Override
    public void onDestroy() {

    }
}
