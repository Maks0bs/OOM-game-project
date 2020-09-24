package com.oom.game.main.gameCore;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer implements IRenderer {

    protected final Graphics graphics;

    public Renderer(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void drawImage(BufferedImage image, int posX, int posY, float scaleX, float scaleY) {
        graphics.drawImage(image, posX, posY, (int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY),
                null);
    }

    public Graphics getGraphics() {
        return graphics;
    }
}
