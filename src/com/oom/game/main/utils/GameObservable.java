package com.oom.game.main.utils;

import gameCore.Game;

import java.util.ArrayList;

public class GameObservable<T> {
    /*
        FIXME add this class to UML
     */
    /*
        FIXME change ArrayList to HashSet!!!
     */
    private ArrayList<GameObserver<T> > observers = new ArrayList<>();

    /**
     * Default constructor
     */
    public GameObservable(){
    }

    /**
     *
     * @param observer class instance that wants to listen to changes in current observable
     */
    public void registerObserver(GameObserver<T> observer){
        this.observers.add(observer);
    }

    /**
     *
     * @param observer class instance that needs to stop listening to changes in current observable
     */
    public void unregisterObserver(GameObserver<T> observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(T data){//add old data as argument for comparison
        for (GameObserver<T> o : this.observers){
            o.update(this, data);
        }
    }
}
