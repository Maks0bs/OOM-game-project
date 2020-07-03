package com.oom.game.main.process.render;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.utils.EntityTextures;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

public class EntityRenderable extends NodeRenderable {

    private Entity entity;
    private String curAppearance = null;
    private GameObserver<Entity> observer = null;

    /**
     *
     * @param entity the entity that the image defines this renderable
     */
    public EntityRenderable(Entity entity) {
        super(null, entity.getPosition());
        this.image = EntityTextures.getTextureByAppearance(entity.getAppearance());
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
