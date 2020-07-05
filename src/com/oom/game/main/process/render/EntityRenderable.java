package com.oom.game.main.process.render;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.NPC;
import com.oom.game.main.entities.utils.EntityTextures;
import com.oom.game.main.environment.Position;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
import gameCore.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityRenderable extends NodeRenderable {

    private Entity entity;
    private String curAppearance = null;
    private GameObserver<Entity> observer = null;
    private transient BufferedImage sleepingEntityImage = null;

    /**
     *
     * @param entity the entity that the image defines this renderable
     */
    public EntityRenderable(Entity entity) {
        super(null, entity.getPosition());
        this.image = EntityTextures.getTextureByAppearance(entity.getAppearance());
        this.sleepingEntityImage = GUIRenderable.deepCopy(
                EntityTextures.getTextureByAppearance(entity.getAppearance())
        );

        Graphics g = sleepingEntityImage.createGraphics();
        Font f = new Font("Dialog", Font.PLAIN, 12);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("Sleeping", 0, 12);
        g.dispose();

        observer = new GameObserver<Entity>() {
            @Override
            public void update(GameObservable<Entity> observable, Entity newData) {
                if (!curAppearance.equals(newData.getAppearance())){
                    image = EntityTextures.getTextureByAppearance(newData.getAppearance());
                }

                curAppearance = newData.getAppearance();
            }
        };
        entity.getObservable().registerObserver(observer);

        this.entity = entity;
        curAppearance = entity.getAppearance();
    }


    @Override
    public void render(Renderer renderer) {
        if (entity instanceof NPC){
            NPC n = (NPC) entity;
            if (n.getState() == n.getSleepingState()){
                this.image = sleepingEntityImage;
            }
        }
        super.render(renderer);
        this.image = EntityTextures.getTextureByAppearance(entity.getAppearance());
    }

    @Override
    public void render(Renderer renderer, Position pos) {
        if (entity instanceof NPC){
            NPC n = (NPC) entity;
            if (n.getState() == n.getSleepingState()){
                this.image = sleepingEntityImage;
            }
        }
        super.render(renderer, pos);

        this.image = EntityTextures.getTextureByAppearance(entity.getAppearance());
    }

    @Override
    protected String getNodeType() {
        return "Block";
    }

    /**
     * FIXME we might not need to use this method at all
     * Right now it is used to avoid memory leaks in Observable,
     * because when this EntityRenderable is deleted in WorldRenderable,
     * this object is null, but it still exists in list of objects in observer
     * @throws Throwable see {@link Object}
     */
    @Override
    @Deprecated
    protected void finalize() throws Throwable {
        entity.getObservable().unregisterObserver(observer);
        super.finalize();
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
