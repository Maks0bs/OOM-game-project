package com.oom.game.main.process.render;

import com.oom.game.main.environment.Position;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class NodeRenderable implements IRenderable, Serializable {

    protected transient BufferedImage image;
    protected Position position;
    protected float scaleX = 1, scaleY = 1;
    protected Renderer renderer = null;



    /**
     *
     * @param image image in this object that can be rendered
     * @param position position on frame
     * @param scaleX coefficient that defines how picture should be enlarged on x-axis
     * @param scaleY coefficient that defines how picture should be enlarged on y-axis
     */
    public NodeRenderable(BufferedImage image, Position position, float scaleX, float scaleY) {
        this.image = image;
        this.position = position;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     *
     * @param image image in this object that can be rendered
     * @param position position on frame
     */
    public NodeRenderable(BufferedImage image, Position position) {
        this.image = image;
        this.position = position;
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        renderer.drawImage(
                image,
                position.getX(),
                position.getY(),
                scaleX,
                scaleY
        );
    }

    /**
     * {@link IRenderable}
     * @param pos relative position of rendering
     */
    public void render(Renderer renderer, Position pos) {
        renderer.drawImage(
                image,
                pos.getX(),
                pos.getY(),
                scaleX,
                scaleY
        );
    }

    /**
     * this method mainly exists to make NodeRenderable abstract and restrict its instantiation
     * @return type of current NodeRenderable (e. g. Block or Entity)
     */
    protected abstract String getNodeType();

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
