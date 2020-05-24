package com.oom.game.main.process;

import com.oom.game.main.ui.utils.GUITextures;
import com.oom.game.main.utils.GameObservable;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;
import gameCore.eventSystem.IEvent;
import gameCore.eventSystem.IEventListener;
import gameCore.input.keyboard.KeyPressedEvent;
import gameCore.input.keyboard.KeyReleasedEvent;

import java.util.ArrayList;
import java.util.HashSet;

public class MainRenderable implements IRenderable, IUpdatable, IEventListener {

    private Renderer renderer = null; //FIXME encapsulate renderer
    private WorldRenderable worldRenderable = null;
    private GUIRenderable guiRenderable = null;
    private HashSet<Character> pressedKeys = new HashSet<>();
    private int framesWhilePressed = 0;
    private GameObservable<MainRenderable> observable = new GameObservable<>();
    //FIXME add GUIRenderable

    public MainRenderable(WorldRenderable worldRenderable, GUIRenderable guiRenderable){
        this.worldRenderable = worldRenderable;
        this.guiRenderable = guiRenderable;
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        worldRenderable.render(renderer);
        guiRenderable.render(renderer);
    }

    /**
     * {@link IRenderable}
     */
    public void render(){
        if (pressedKeys.size() > 0){
            framesWhilePressed++;
            this.observable.notifyObservers(this);
        }
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

    /**
     * {@link IEventListener}
     */
    @Override
    public void onEvent(IEvent event) {
        if (event instanceof KeyPressedEvent){
            pressedKeys.add(((KeyPressedEvent) event).getKeySymbol() );
        } else if (event instanceof KeyReleasedEvent){
            pressedKeys.remove(((KeyReleasedEvent) event).getKeySymbol() );
            framesWhilePressed = 0;
        }
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public WorldRenderable getWorldRenderable() {
        return worldRenderable;
    }

    public void setWorldRenderable(WorldRenderable worldRenderable) {
        this.worldRenderable = worldRenderable;
    }

    public int getFramesWhilePressed() {
        return framesWhilePressed;
    }

    public GameObservable<MainRenderable> getObservable() {
        return observable;
    }
}
