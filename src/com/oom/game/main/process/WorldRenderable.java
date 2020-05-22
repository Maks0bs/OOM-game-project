package com.oom.game.main.process;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;

import java.awt.*;
import java.util.ArrayList;

public class WorldRenderable implements IRenderable, IUpdatable {
    /*
        FIXME add this class to UML

        FIXME add observable / observer pattern
        FIXME Renderer = observer, key and click listeners + data classes are observables
     */


    private Renderer renderer = null; //FIXME encapsulate renderer
    /*
        the array renderables is only going to have items that have to be rendered on screen.
        i. e blocks that fit into window size and such entities
     */
    private ArrayList<NodeRenderable> renderables = new ArrayList<>();
    /*
        No setters for world, width, height, because they should not be changed
        as WorldRenderable is a separate static rendering process that only changes
        when blocks / entities on screen change
     */
    private World world;
    private int width, height;
    private Position startingPosition;

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
        for (int i = 0; i < height / World.BLOCK_SIZE; i++){
            for (int j = 0; j < width / World.BLOCK_SIZE; j++){
                NodeRenderable cur = new NodeRenderable(
                )
            }
        }
    }

    /**
     * This function should update the list of renderables according to the new stuff that should be displayed
     * @param newPosition new position of top left corner of rendering
     */
    public void updatePosition(Position newPosition){
        //FIXME check expansion in 4 directions
        //FIXME e. g. : expansion to right: (prev position + size).getblockX >= newPosition.getBlockX...
    }

    /**
     *
     * @param renderable item to add to list of renderable object in this global renderer
     */
    public void addRenderable(NodeRenderable renderable){
        renderables.add(renderable);
    }

    /*
        FIXME add methods that can sort renderables, find them somehow and return to caller
     */

    /**
     * @param renderable renderable to remove from the list that this renderer is responsible for
     */
    public void removeRenderable(NodeRenderable renderable){
        renderables.remove(renderable);
    }

    /**
     * {@link IUpdatable}
     */
    @Override
    public void render(Renderer renderer) {
        for (NodeRenderable r : renderables){
            r.render(renderer);
        }
    }


    /**
     * {@link IUpdatable}
     */
    public void render(){
        render(this.renderer);
    }

    /**
     * {@link IUpdatable}
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
