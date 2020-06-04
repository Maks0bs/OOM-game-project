package com.oom.game.main.utils;

import gameCore.Game;
import jdk.jfr.Description;

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

    /**
     * Notify all registered observers about the update of data
     * @param data new data to notify observers about
     */
    public void notifyObservers(T data){//add old data as argument for comparison
        for (GameObserver<T> o : this.observers){
            o.update(this, data);
        }
    }

    /**
     * Notify all registered observers about the update of data
     * @param data new data to notify observers about
     * @param specs object to specify what exactly has changed
     */
    public void notifyObservers(T data, Object specs){
        for (GameObserver<T> o : this.observers){
            o.update(this, data, specs);
        }
    }

    /**
     * Notify all registered observers about the update of data
     * @param data new data to notify observers about
     * @param specs object to specify what exactly has changed
     * @param id id of the action that caused the update (specified in classes)
     */
    public void notifyObservers(T data, Object specs, int id){
        for (GameObserver<T> o : this.observers){
            o.update(this, data, specs, id);
        }
    }

    @Override
    public String toString() {
        String ans = "GameObservable" + " with " + observers.size() + " observers: ";
        for (int i = 0; i < observers.size(); i++){
            ans += observers.get(i).getClass().toString() + " ";
        }

        return ans;
    }
}
