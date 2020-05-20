package com.oom.game.main.utils;

public interface GameObserver<T> {
    /*
        FIXME add this class to UML
     */
    void update(GameObservable<T> observable, T newData);
}
