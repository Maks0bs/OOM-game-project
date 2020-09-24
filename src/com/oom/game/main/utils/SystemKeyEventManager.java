package com.oom.game.main.utils;


import com.oom.game.main.gameCore.eventSystem.IEvent;
import com.oom.game.main.gameCore.eventSystem.IEventListener;
import com.oom.game.main.gameCore.eventSystem.IEventManager;

import java.util.ArrayList;

public final class SystemKeyEventManager implements IEventManager {
    private ArrayList<IEventListener> observers = new ArrayList<>();

    private static SystemKeyEventManager instance = null;

    /**
     * Default private constructor for singleton to function
     */
    private SystemKeyEventManager(){

    }

    /**
     *
     * @return the only instance of system key event manager [singleton]
     */
    public static SystemKeyEventManager getInstance() {
        if (instance == null){
            instance = new SystemKeyEventManager();
        }
        return instance;
    }

    /**
     * {@link IEventManager}
     */
    @Override
    public void register(IEventListener eventListener) {
        observers.add(eventListener);
    }

    /**
     * {@link IEventManager}
     */
    @Override
    public void unregister(IEventListener eventListener) {
        observers.remove(eventListener);
    }

    /**
     * {@link IEventManager}
     */
    @Override
    public void fire(IEvent event) {
        for (int i = 0; i < observers.size(); i++){
            IEventListener cur = observers.get(i);
            cur.onEvent(event);
        }
    }
}
