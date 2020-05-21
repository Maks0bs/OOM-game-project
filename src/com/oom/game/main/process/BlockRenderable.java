package com.oom.game.main.process;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.blocks.utils.BlockTextures;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockRenderable extends NodeRenderable implements GameObserver<Block> {
    /*
        FIXME add this class to UML
     */
    private Block block;

    /**
     *
     * @param block the block that the image defines this renderable
     * @param position position on frame
     */
    public BlockRenderable(Block block, Position position) {
        super(null, position);
        this.image = BlockTextures.getTextureByState(block.getState());
        block.getObservable().registerObserver(this);
        this.block = block;
    }

    @Override
    public void update(GameObservable<Block> observable, Block newData) {


        //if (!newData.getState().equals(this.block.getState())){ this could work, if data was set in setter AFTER sending it over here
        if (newData.hasBlockOnTop()){
            BufferedImage bottom = BlockTextures.getTextureByState(newData.getState());
            BufferedImage top = BlockTextures.getTextureByState(newData.getBlockOnTop().getState());
            BufferedImage combined = new BufferedImage(bottom.getWidth(), bottom.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = combined.getGraphics();
            g.drawImage(bottom, 0, 0, null);
            g.drawImage(top, 0, 0, null);
            g.dispose();
            this.image = combined;
        }
        else{
            this.image = BlockTextures.getTextureByState(newData.getState());
        }

        this.block = newData;
    }

    @Override
    protected String getNodeType() {
        return "Block";
    }

    @Override
    public void render(Renderer renderer) {
        super.render(renderer);
    }

    /**
     * FIXME we might not need to use this method at all
     * Right now it is used to avoid memory leaks in Observable,
     * because when this BlockRenderable is deleted in WorldRenderable,
     * this object is null, but it still exists in list of objects in observer
     * @throws Throwable see {@link Object}
     */
    @Override
    protected void finalize() throws Throwable {
        block.getObservable().unregisterObserver(this);
        super.finalize();
    }
}
