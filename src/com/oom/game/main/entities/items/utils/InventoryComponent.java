package com.oom.game.main.entities.items.utils;

import java.util.Iterator;

public interface InventoryComponent extends Iterable<InventoryComponent> {

    /**
     *
     * @return get the name of the item (equal to state for displaying entities)
     */
    String getName();
    /**
     * add a child to the current component
     */
    void add(InventoryComponent component);

    /**
     * remove a child from the current component
     */
    void remove(InventoryComponent component);

    /**
     *
     * @param i the number of the child to return
     * @return the chosen child
     */
    InventoryComponent getChild(int i);

    /**
     *
     * @return iterator to go through direct children of current component (no recursion)
     */
    @Override
    Iterator<InventoryComponent> iterator();


}
