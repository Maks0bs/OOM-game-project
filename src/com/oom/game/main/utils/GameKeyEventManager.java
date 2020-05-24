package com.oom.game.main.utils;

import gameCore.eventSystem.IEvent;
import gameCore.eventSystem.IEventListener;
import gameCore.eventSystem.IEventManager;
import gameCore.input.keyboard.KeyEvent;

import java.util.ArrayList;
import java.util.EventListener;

public class GameKeyEventManager implements IEventManager {
    private ArrayList<IEventListener> observers = new ArrayList<>();

    @Override
    public void register(IEventListener eventListener) {
        observers.add(eventListener);
    }

    @Override
    public void unregister(IEventListener eventListener) {
        observers.remove(eventListener);
    }

    @Override
    public void fire(IEvent event) {
        for (int i = 0; i < observers.size(); i++){
            IEventListener cur = observers.get(i);
            cur.onEvent(event);
        }
    }
}
