package com.oom.game.main.process.render;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.utils.EntityTextures;
import com.oom.game.main.environment.Position;
import com.oom.game.main.process.render.NodeRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
import gameCore.Renderer;

public class EntityRenderable extends NodeRenderable implements GameObserver<Entity> {
    /*
        FIXME add this class to UML
     */
    private Entity entity;
    private String curState = null;

    /**
     *
     * @param entity the entity that the image defines this renderable
     */
    public EntityRenderable(Entity entity) {
        super(null, entity.getPosition());
        this.image = EntityTextures.getTextureByState(entity.getState());
        entity.getObservable().registerObserver(this);
        this.entity = entity;
        curState = entity.getState();
    }

    @Override
    public void update(GameObservable<Entity> observable, Entity newData) {
        if (!curState.equals(newData.getState())){
            this.image = EntityTextures.getTextureByState(newData.getState());
        }

        curState = newData.getState();
        //FIXME maybe change state to animate control
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
        entity.getObservable().unregisterObserver(this);
        super.finalize();
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
