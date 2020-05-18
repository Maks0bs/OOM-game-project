package com.oom.game.main.process;

import com.oom.game.main.environment.World;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;

import java.awt.Graphics;
import java.util.ArrayList;

public class WorldRenderable implements IRenderable, IUpdatable {
    /*
        FIXME add this class to UML

        FIXME add observable / observer pattern
        FIXME Renderer = observer, key and click listeners + data classes are observables
     */


    private Renderer renderer = null; //FIXME encapsulate renderer
    private ArrayList<NodeRenderable> renderables = new ArrayList<>();

    /**
     * Default constructor
     */
    public WorldRenderable(){
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
