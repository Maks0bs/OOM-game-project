package com.oom.game.main.gameCore;

import java.awt.image.BufferedImage;

interface IRenderer {

    /**
     * Draws a picture in original size
     * @param image the image
     * @param posX  the x-position
     * @param posY  the y-position
     */
    public default void drawImage(BufferedImage image, int posX, int posY) {
        drawImage(image, posX, posY, 1.0f, 1.0f);
    }

    /**
     * Draws a picture size
     * @param image the image
     * @param posX  the x-position
     * @param posY  the y-position
     * @param scaleX the coefficient which defines how much the picture should be scaled
     *               in terms of x-coordinate
     * @param scaleY the coefficient which defines how much the picture should be scaled
     *               in terms of y-coordinate
     */
    public void drawImage(BufferedImage image, int posX, int posY, float scaleX, float scaleY);
}
