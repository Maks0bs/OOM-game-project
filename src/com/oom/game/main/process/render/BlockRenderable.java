package com.oom.game.main.process.render;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.blocks.utils.BlockTextures;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.gameCore.Renderer;

import java.awt.image.BufferedImage;

public class BlockRenderable extends NodeRenderable {

    private Block block;
    //Very bad solution - remembering last block on top
    private BlockRenderable topRenderable = null;
    private boolean displayTop = false;

    /**
     *
     * @param block the block that the image defines this renderable
     * @param position position on frame
     */
    public BlockRenderable(Block block, Position position) {
        super(null, position);
        this.image = BlockTextures.getTextureByAppearance(block.getAppearance());
        this.block = block;
    }

    //FIXME IMPORTANT the situation when block on top gets removed is not handled here!!!!!!!

    /**
     * function to toggle display the block on top of the one that is saved in BlockRenderable as a member
     */
    public void displayTopBlock(){
        displayTop = true;
        if (block.hasBlockOnTop()){
            BufferedImage bottom = BlockTextures.getTextureByAppearance(block.getAppearance());
            BufferedImage top = BlockTextures.getTextureByAppearance(block.getBlockOnTop().getAppearance());
            topRenderable = new BlockRenderable(block.getBlockOnTop(), this.position);

            /*BufferedImage combined = new BufferedImage(bottom.getWidth(), bottom.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = combined.getGraphics();
            g.drawImage(bottom, 0, 0, null);
            g.drawImage(top, 0, 0, null);
            g.dispose();
            this.image = combined;*/
        }
        else{
            topRenderable = null;
        }
    }

    @Override
    public void render(Renderer renderer, Position pos) {
        super.render(renderer, pos);
        if (displayTop && topRenderable != null){
            topRenderable.render(renderer, pos);
        }
    }

    @Override
    public void render(Renderer renderer) {
        super.render(renderer);
        if (displayTop && topRenderable != null){
            topRenderable.render(renderer);
        }
    }

    @Override
    protected String getNodeType() {
        return "Block";
    }

    public BlockRenderable getTopRenderable() {
        return topRenderable;
    }

    public void setTopRenderable(BlockRenderable topRenderable) {
        this.topRenderable = topRenderable;
    }

    public boolean isDisplayTop() {
        return displayTop;
    }

    public void setDisplayTop(boolean displayTop) {
        this.displayTop = displayTop;
    }
}
