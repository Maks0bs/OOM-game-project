package com.oom.game.main.process.render;

import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.World;
import com.oom.game.main.process.utils.control.PlayerControl;
import com.oom.game.main.utils.GameObservable;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;
import gameCore.eventSystem.IEvent;
import gameCore.eventSystem.IEventListener;
import gameCore.input.keyboard.KeyPressedEvent;
import gameCore.input.keyboard.KeyReleasedEvent;

import java.util.ArrayList;
import java.util.HashMap;

/*
    This class has to deal with all system events like key strokes, clicks, etc.
    It then gives data to its correspondent Process with the help of observers
 */
public class MainRenderable implements IRenderable, IUpdatable {

    private Renderer renderer = null; //FIXME encapsulate renderer
    private WorldRenderable worldRenderable = null;
    private GUIRenderable guiRenderable = null;
    /*
        This observable is mainly used to let subscribers know about tick / frame updates in game (currently 30 timer per second)
     */
    private GameObservable<MainRenderable> observable = new GameObservable<>();

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
        render(this.renderer);
    }

    /*
     *  FIXME might not need to call this method here
     *  FIXME might need to put it only to NodeRenderable to update only those components that have actually changed
     */
    /**
     * {@link IUpdatable}
     *
     */
    @Override
    public void update(long elapsedMillis) {
        this.observable.notifyObservers(this);
        render(this.renderer);
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public WorldRenderable getWorldRenderable() {
        return worldRenderable;
    }


    public GameObservable<MainRenderable> getObservable() {
        return observable;
    }

}
