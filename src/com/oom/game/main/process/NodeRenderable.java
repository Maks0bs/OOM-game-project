package com.oom.game.main.process;

import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.image.BufferedImage;

public class NodeRenderable implements IRenderable {
    /*
        FIXME add this class to UML
     */
    private BufferedImage image;
    private int posX, posY;
    private float scaleX, scaleY;
    private Renderer renderer = null;//FIXME encapsulate renderer

    /**
     *
     * @param image image in this object that can be rendered
     * @param posX position on x-axis
     * @param posY position on y-axis
     * @param scaleX coefficient that defines how picture should be enlarged on x-axis
     * @param scaleY coefficient that defines how picture should be enlarged on y-axis
     */
    public NodeRenderable(BufferedImage image, int posX, int posY, float scaleX, float scaleY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        renderer.drawImage(image, posX, posY, scaleX, scaleY);
    }

    /**
     * {@link IRenderable} render this.renderer
     */
    public void render(){
        render(this.renderer);
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }
}
