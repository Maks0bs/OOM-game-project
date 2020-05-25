package com.oom.game.main.environment.utils;

//this class has to be observable

import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;

public class Block {
    /*
        FIXME might need to replace state string member to int variable that can only take up certain constants, defined in block classes
     */
    protected String state = "Default"; //State defines the texture that will be used to display this block
    protected Block blockOnTop = null;
    protected PlayerInteraction playerInteraction = null;
    protected WalkAction walkAction = null;
    protected DamageAction damageAction = null;
    protected MoveAction moveAction = null;
    //FIXME URGENT implement strategies that imply no action to use instead of null!!!!!!!!!!!!!
    //FIXME !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /*
        It's possible to implement this through extending from observable class, but it's not possible
        to extend in such a way in Player class. That's why this pattern is used.
     */
    protected GameObservable<Block> observable = new GameObservable<Block>();

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
     * FIXME add this method to UML, because it has to do with observable
     * @param state new state
     */
    public void setState(String state) {
        this.state = state;
        this.observable.notifyObservers(this);
    }

    public Block getBlockOnTop() {
        return blockOnTop;
    }

    /**
     * FIXME add this method to UML, because it has to do with observable
     * @param blockOnTop new blockOnTop
     */
    public void setBlockOnTop(Block blockOnTop) {
        //FIXME sometimes this function gets called 2 times (noticed on initialization)
        this.blockOnTop = blockOnTop;
        this.observable.notifyObservers(this);
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

    public GameObservable<Block> getObservable() {
        return observable;
    }

    public void setObservable(GameObservable<Block> observable) {
        this.observable = observable;
    }
}