package com.oom.game.main.process;

import com.oom.game.main.utils.GameObservable;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;
import gameCore.eventSystem.IEvent;
import gameCore.eventSystem.IEventListener;
import gameCore.input.keyboard.KeyPressedEvent;
import gameCore.input.keyboard.KeyReleasedEvent;

import java.util.HashMap;
import java.util.Set;

/*
    This class has to deal with all system events like key strokes, clicks, etc.
    It then gives data to its correspondent Process with the help of observers
 */
public class MainRenderable implements IRenderable, IUpdatable, IEventListener {

    private Renderer renderer = null; //FIXME encapsulate renderer
    private WorldRenderable worldRenderable = null;
    private GUIRenderable guiRenderable = null;
    private GameObservable<MainRenderable> observable = new GameObservable<>();
    private HashMap<Character, Integer> pressedTime = new HashMap<>();

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
        if (pressedTime.size() > 0){
            //FIXME using iterators here might cause problems with multithreading
            Character[] keys = new Character[pressedTime.keySet().size()];
            pressedTime.keySet().toArray(keys);
            for (int i = 0; i < keys.length; i++){
                Character c = keys[i];
                int curValue = pressedTime.get(c);
                pressedTime.put(c, curValue + 1);
            }
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
            Character cur = ((KeyPressedEvent) event).getKeySymbol();
            if (!pressedTime.containsKey(cur)) {
                pressedTime.put(cur, 0);
            }
        } else if (event instanceof KeyReleasedEvent){
            Character cur = ((KeyReleasedEvent) event).getKeySymbol();
            pressedTime.remove(cur);
        }
    }

    /**
     *
     * @param c character responsible for pressed key
     * @return the amount of frames that this key has been kept pressed for
     */
    public int getKeyPressedTime(Character c){
        return pressedTime.get(c);
    }

    /**
     *
     * @return true if some keys are currently pressed, false otherwise
     */

    public boolean keyIsPressed(Character c){
        return pressedTime.containsKey(c);
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

    public GameObservable<MainRenderable> getObservable() {
        return observable;
    }

}
