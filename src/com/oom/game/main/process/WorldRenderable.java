package com.oom.game.main.process;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.EmptyVoid;
import com.oom.game.main.environment.blocks.utils.BlockTextures;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;

/*
    NOTES: this class should invoke updates itself
    updates occur, only when the correspondent World class with data gets changed.
    This one is only responsible for rendering

    World should always be bigger in all dimensions than the renderable
    FIXME might need to fix some issues in above notes
 */

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class WorldRenderable implements IRenderable, IUpdatable {
    /*
        FIXME add this class to UML

        FIXME add observable / observer pattern
        FIXME Renderer = observer, key and click listeners + data classes are observables
     */


    private Renderer renderer = null; //FIXME encapsulate renderer
    /*
        No setters for world, width, height, because they should not be changed
        as WorldRenderable is a separate static rendering process that only changes
        when blocks / entities on screen change
     */
    private World world;
    private int width, height;
    private Position startingPosition;
    /*
        There are separate arrays for saving blocks and creatures only because of optimisation
        We could call World class getBlock method and create new NodeRederables every time
        but it is VERY VERY inefficient (might even cause lag)

        This array of blocks is extended by 1 on x-axis and y-axis.
        There are placeholder EmptyVoids in case the some updates occur and we have to move the rendered component
     */
    private ArrayList<ArrayList<BlockRenderable> > blockRenderables = new ArrayList<>();
    private ArrayList<NodeRenderable> entityRenderables = new ArrayList<>();

    /**
     * Default constructor
     */
    public WorldRenderable(World world, int width, int height){
        this.world = world;
        this.width = width;
        this.height = height;
        this.startingPosition = new Position(0, 0, false);
        //FIXME fill up starting renderables array with initial blocks
        //FIXME later add entities
        ArrayList<BlockRenderable> placeholderY = new ArrayList<>();
        for (int i = 0; i <= height / World.BLOCK_SIZE; i++){
            ArrayList<BlockRenderable> temp = new ArrayList<>();
            for (int j = 0; j <= width / World.BLOCK_SIZE; j++){
                BlockRenderable cur = new BlockRenderable(
                        world.getBlock(i, j),
                        new Position(i, j, true)
                );

                temp.add(cur);
            }
            BlockRenderable placeholderX = new BlockRenderable(
                    new EmptyVoid(),
                    new Position(i, width / (World.BLOCK_SIZE + 1), true)
            );
            temp.add(placeholderX);
            blockRenderables.add(temp);
            placeholderY.add(placeholderX);
        }
        blockRenderables.add(placeholderY);
    }

    /**
     * This function should update the list of renderables according to the new stuff that should be displayed
     * @param newPosition new position of top left corner of rendering
     */
    public void updatePosition(Position newPosition){
        //FIXME implement this method
        /* FIXME urgent
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
            * !
         */
        //FIXME check expansion in 4 directions
        //FIXME e. g. : expansion to right: (prev position + size).getblockX >= newPosition.getBlockX...
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        for (ArrayList<BlockRenderable> line : blockRenderables){
            for (NodeRenderable r : line) {
                r.render(renderer);
            }
        }

        for (NodeRenderable r : entityRenderables){
            r.render(renderer);
        }
    }


    /**
     * {@link IRenderable}
     */
    public void render(){
        render(this.renderer);
    }

    /**
     * {@link IUpdatable}
     * FIXME might not need to call this method here
     * FIXME might need to put it only to NodeRenderable to update only those components that have actually changed
     */
    @Override
    public void update(long elapsedMillis) {
        render();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
