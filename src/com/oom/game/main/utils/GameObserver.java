package com.oom.game.main.utils;

public interface GameObserver<T> {

    /**
     *
     * @param observable observable that caused the update (for reference)
     * @param newData new data that the observable has obtained
     */
    void update(GameObservable<T> observable, T newData);

    /**
     *
     * @param observable observable that caused the update (for reference)
     * @param newData new data that the observable has obtained
     * @param specs object to specify what exactly has changed
     */
    default void update(GameObservable<T> observable, T newData, Object specs){
        update(observable, newData);
    }
    /*
        FIXME might need to add another update method to specify what class the specs are
     */

    /**
     *
     * @param observable observable that caused the update (for reference)
     * @param newData new data that the observable has obtained
     * @param specs object to specify what exactly has changed
     * @param id id of the action that caused the update (specified in classes)
     */
    default void update(GameObservable<T> observable, T newData, Object specs, int id) {
        update(observable, newData, specs);
    }
}
