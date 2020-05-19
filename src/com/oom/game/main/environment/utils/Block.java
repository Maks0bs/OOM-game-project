package com.oom.game.main.environment.utils;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.blocks.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class Block {
    protected String texturePath = "res/Default32px.png";
    //FIXME add some member to describe current state (apart from implemented strategies) like type
    protected Block blockOnTop = null;
    protected PlayerInteraction playerInteraction = null;
    protected WalkAction walkAction = null;
    protected DamageAction damageAction = null;
    protected MoveAction moveAction = null;

    /**
     *
     * @param texture texture of the block (currently int)
     */
    public Block(String texture){
        this.texturePath = texture;
    }

    /**
     *
     * @return true if the current block has one on top, false otherwise
     */
    public boolean hasBlockOnTop(){
        return (this.blockOnTop != null);
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public Block getBlockOnTop() {
        return blockOnTop;
    }

    public void setBlockOnTop(Block blockOnTop) {
        this.blockOnTop = blockOnTop;
    }

    public PlayerInteraction getPlayerInteraction() {
        return playerInteraction;
    }

    public void setPlayerInteraction(PlayerInteraction playerInteraction) {
        this.playerInteraction = playerInteraction;
    }

    public WalkAction getWalkAction() {
        return walkAction;
    }

    public void setWalkAction(WalkAction walkAction) {
        this.walkAction = walkAction;
    }

    public DamageAction getDamageAction() {
        return damageAction;
    }

    public void setDamageAction(DamageAction damageAction) {
        this.damageAction = damageAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(MoveAction moveAction) {
        this.moveAction = moveAction;
    }


}