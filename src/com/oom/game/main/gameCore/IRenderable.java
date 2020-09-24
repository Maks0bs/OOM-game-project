package com.oom.game.main.gameCore;

public interface IRenderable {

    /**
     * Gets called during an update
     * @param renderer the renderer object which should draw the content
     */
    public void render(Renderer renderer);
}
