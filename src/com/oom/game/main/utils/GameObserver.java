package com.oom.game.main.utils;

public interface GameObserver<T> {

    void update(GameObservable<T> observable, T newData);
}
