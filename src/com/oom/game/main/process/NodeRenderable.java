package com.oom.game.main.process;

import com.oom.game.main.environment.Position;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.image.BufferedImage;

public class NodeRenderable implements IRenderable {
    /*
        FIXME add this class to UML
     */
    private BufferedImage image; //FIXME bind image to the correspondent entity
    private Position position;
    private float scaleX, scaleY;
    private Renderer renderer = null;//FIXME encapsulate renderer

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
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        renderer.drawImage(
                image,
                position.getX(),
                position.getY(),
                scaleX,
                scaleY);
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
