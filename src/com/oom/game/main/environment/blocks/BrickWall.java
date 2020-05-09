package com.oom.game.main.environment.blocks;

import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.environment.utils.Destructable;


public class BrickWall extends Block implements Destructable {
    //FIXME add this interface to UML
    public static final double DEFAULT_THICKNESS = 1.0;
    private double thickness;

    /**
     *
     * @param texture {@link Block}
     * @param thickness thickness of the block (may be used to define when it can be destroyed)
     */
    BrickWall(int texture, double thickness){
        super(texture);
        this.thickness = thickness;
    }


    /**
     * {@link Destructable}
     */
    @Override
    public void onDestroy() {

    }
}
