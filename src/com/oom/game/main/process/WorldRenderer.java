package com.oom.game.main.process;

import com.oom.game.main.environment.World;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;

import java.util.ArrayList;

public class WorldRenderer implements IRenderable {
    /*
        FIXME add this class to UML
     */


    private Renderer renderer = null; //FIXME encapsulate renderer
    private ArrayList<NodeRenderable> renderables = new ArrayList<>();

    public WorldRenderer(){

    }

    public void addRenderable(NodeRenderable renderable){
        renderables.add(renderable);
    }

    public void setRenderable(int i, NodeRenderable renderable){
        renderables.set(i, renderable);
    }

    /**
     * {@link IUpdatable}
     */
    @Override
    public void render(Renderer renderer) {
        for (NodeRenderable r : renderables){
            renderer.drawImage(
                    r.getImage(),
                    r.getPosX(),
                    r.getPosY(),
                    r.getScaleX(),
                    r.getScaleY()
            );
        }
    }

    public void render(){
        render(this.renderer);
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
