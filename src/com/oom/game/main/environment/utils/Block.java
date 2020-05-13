package com.oom.game.main.environment.utils;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.environment.blocks.*;


public class Block {
    protected int texture; // TODO to be replaced with regular graphical texture
    protected Block blockOnTop = null;
    protected PlayerInteraction playerInteraction = null;
    protected WalkAction walkAction = null;
    protected DamageAction damageAction = null;
    protected MoveAction moveAction = null;

    /**
     *
     * @param texture texture of the block (currently int)
     */
    public Block(int texture){
        this.texture = texture;
    }

    /**
     *
     * @return true if the current block has one on top, false otherwise
     */
    public boolean hasBlockOnTop(){
        return (this.blockOnTop != null);
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
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