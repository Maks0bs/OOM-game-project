package com.oom.game.main.process;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.utils.EntityTextures;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.blocks.utils.BlockTextures;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityRenderable extends NodeRenderable implements GameObserver<Entity> {

    private Entity entity;

    /**
     *
     * @param entity the entity that the image defines this renderable
     */
    public EntityRenderable(Entity entity) {
        super(null, entity.getPosition());
        this.image = EntityTextures.getTextureByState(entity.getState());
        entity.getObservable().registerObserver(this);
        this.entity = entity;
    }

    @Override
    public void update(GameObservable<Entity> observable, Entity newData) {
        this.entity = newData;
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
}
