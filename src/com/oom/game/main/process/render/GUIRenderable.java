package com.oom.game.main.process.render;

import com.oom.game.main.environment.Position;
import com.oom.game.main.process.render.NodeRenderable;
import com.oom.game.main.ui.utils.GUITextures;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.image.BufferedImage;

public class GUIRenderable implements IRenderable /*,IUpdatable*/ {

    private NodeRenderable test;

    /**
     * Default constructor
     */
    public GUIRenderable() {
        BufferedImage img = GUITextures.getTextureByState("GuiTest");
        test = new NodeRenderable(img, new Position(10, 10, true), 1, 1) {
            @Override
            protected String getNodeType() {
                return null;
            }
        };
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        test.render(renderer);
    }
}
