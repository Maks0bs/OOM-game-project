package com.oom.game.main.environment.utils;

import com.oom.game.main.environment.blocks.strategies.NoneMoveAction;
import com.oom.game.main.environment.blocks.strategies.NonePlayerInteraction;
import com.oom.game.main.environment.blocks.strategies.NoneSolidBlockAction;
import com.oom.game.main.environment.blocks.strategies.NoneWalkAction;
import com.oom.game.main.utils.GameObservable;

import java.io.Serializable;

public class Block implements Serializable {
    /*
        FIXME might need to replace state string member to int variable that can only take up certain constants, defined in block classes
     */
    protected String state = "Default"; //State defines the texture that will be used to display this block
    protected Block blockOnTop = null;
    protected PlayerInteraction playerInteraction = new NonePlayerInteraction();
    protected WalkAction walkAction = new NoneWalkAction();
    protected SolidBlockAction solidBlockAction = new NoneSolidBlockAction();
    protected MoveAction moveAction = new NoneMoveAction();
    /*
        It's possible to implement this through extending from observable class, but it's not possible
        to extend in such a way in Player class. That's why this pattern is used.
     */

    /**
     *
     * @param state state of the block (currently int)
     */
    public Block(String state){
        this.state = state;
    }

    /**
     *
     * @return true if the current block has one on top, false otherwise
     */
    public boolean hasBlockOnTop(){
        return (this.blockOnTop != null);
    }

    public String getState() {
        return state;
    }

    /**
     * @param state new state
     */
    public void setState(String state) {
        this.state = state;
    }

    public Block getBlockOnTop() {
        return blockOnTop;
    }

    /**
     * @param blockOnTop new blockOnTop
     */
    public void setBlockOnTop(Block blockOnTop) {
        //FIXME sometimes this function gets called 2 times (noticed on initialization)
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

    public SolidBlockAction getSolidBlockAction() {
        return solidBlockAction;
    }

    public void setSolidBlockAction(SolidBlockAction solidBlockAction) {
        this.solidBlockAction = solidBlockAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(MoveAction moveAction) {
        this.moveAction = moveAction;
    }

}