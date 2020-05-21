package com.oom.game.main.process;

import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.EmptyVoid;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;

/*
    NOTES: this class should invoke updates itself
    updates occur, only when the correspondent World class with data gets changed.
    This one is only responsible for rendering

    World should always be bigger in all dimensions than the renderable
    FIXME might need to fix some issues in above notes
 */

import java.util.ArrayList;

public class WorldRenderable implements IRenderable, IUpdatable {
    /*
        FIXME add this class to UML

        FIXME add observable / observer pattern
        FIXME Renderer = observer, key and click listeners + data classes are observables
     */


    private Renderer renderer = null; //FIXME encapsulate renderer
    /*
        No setters for world, width, height, because they should not be changed
        as WorldRenderable is a separate static rendering process that only changes
        when blocks / entities on screen change
     */
    private World world;
    private int width, height;
    /*
        The current renderable field is described by the position (top left corner)
        and its width and height. All stuff in this area should be rendered as specified
        by the current member values
     */
    private Position position;
    /*
        There are separate arrays for saving blocks and creatures only because of optimisation
        We could call World class getBlock method and create new NodeRederables every time
        but it is VERY VERY inefficient (might even cause lag)

        This array of blocks is extended by 1 on x-axis and y-axis.
        There are placeholder EmptyVoids in case the some updates occur and we have to move the rendered component
     */
    private ArrayList<ArrayList<BlockRenderable> > blockRenderables = new ArrayList<>();
    private ArrayList<NodeRenderable> entityRenderables = new ArrayList<>();

    /**
     * Default constructor
     */
    public WorldRenderable(World world, int width, int height){
        this.world = world;
        this.width = width;
        this.height = height;
        this.position = new Position(0, 0, false);
        //FIXME fill up starting renderables array with initial blocks
        //FIXME later add entities
        for (int i = 0; i <= height / World.BLOCK_SIZE; i++){
            ArrayList<BlockRenderable> temp = new ArrayList<>();
            for (int j = 0; j <= width / World.BLOCK_SIZE; j++){
                BlockRenderable cur = new BlockRenderable(
                        world.getBlock(i, j),
                        new Position(i, j, true)
                );

                temp.add(cur);
            }

            blockRenderables.add(temp);
        }

        //Maybe add placeholder blocks if something goes wrong with visual rendering
    }

    /**
     * This function should update the list of renderables according to the new stuff that should be displayed
     * @param newPosition new position of top left corner of rendering
     * @return true if position was updated successfully, false on problems (mainly boundaries are set wrong)
     */
    public boolean updatePosition(Position newPosition){
        //FIXME implement this method
        int curStartY = this.position.getBlockY(), newStartY = newPosition.getBlockY();
        int curStartX = this.position.getBlockX(), newStartX = newPosition.getBlockX();

        int curEndY = (this.position.getY() + this.height) / World.BLOCK_SIZE,
                newEndY = (newPosition.getY() + this.height) / World.BLOCK_SIZE;
        int curEndX = (this.position.getX() + this.width) / World.BLOCK_SIZE,
                newEndX = (newPosition.getX() + this.width) / World.BLOCK_SIZE;

        int diffStartY = curStartY - newStartY, diffStartX = curStartX - newStartX;
        int diffEndY = curEndY - newEndY, diffEndX = curEndX - newEndX;

        //FIXME return false here if there are problems with boundaries

        //Top side
        if (diffStartY > 0){
            for (int i = curStartY - 1; i >= newStartY; i--){
                ArrayList<BlockRenderable> temp = new ArrayList<>();
                for (int j = curStartX; j <= curEndX; j++){
                    BlockRenderable cur = new BlockRenderable(
                            world.getBlock(i, j),
                            new Position(i, j, true)
                    );

                    temp.add(cur);
                }
                blockRenderables.add(0, temp);
            }
        } else if (diffStartY < 0){
            for (int i = curStartX + 1; i <= newStartX; i++){
                blockRenderables.remove(0);
            }
        }


        //Bottom side
        if (diffEndY > 0){

            System.out.println("delete");
            for (int i = curEndY - 1; i >= newEndY; i--){
                blockRenderables.remove(blockRenderables.size() - 1);
            }
        } else if (diffEndY < 0){
            for (int i = curEndY + 1; i <= newEndY; i++){
                ArrayList<BlockRenderable> temp = new ArrayList<>();
                for (int j = curStartX; j <= curEndX; j++){
                    BlockRenderable cur = new BlockRenderable(
                            world.getBlock(i, j),
                            new Position(i, j, true)
                    );

                    temp.add(cur);
                }
                blockRenderables.add(temp);
            }
        }

        curStartY = newStartY;
        curEndY = newEndY;

        //Left side
        if (diffStartX > 0){
            for (int i = curStartX - 1; i >= newStartX; i--){
                for (int j = curStartY; j <= curEndY; j++){
                    BlockRenderable cur = new BlockRenderable(
                            world.getBlock(j, i),
                            new Position(j, i, true)
                    );
                    blockRenderables.get(j - curStartY).add(0, cur);
                }
            }
        } else if (diffStartX < 0) {
            for (int i = curStartX + 1; i <= newStartX; i++){
                for (int j = curStartY; j <= curEndY; j++){
                    blockRenderables.get(j - curStartY).remove(0);
                }
            }
        }

        //Right side
        if (diffEndX > 0){
            for (int i = curEndX - 1; i >= newEndX; i--){
                int size = blockRenderables.get(0).size();
                for (int j = curStartY; j <= curEndY; j++){
                    blockRenderables.get(j - curStartY).remove(size - 1);
                }
            }
        } else if (diffEndX < 0) {
            for (int i = curEndX + 1; i <= newEndX; i++){
                for (int j = curStartY; j <= curEndY; j++){
                    BlockRenderable cur = new BlockRenderable(
                            world.getBlock(j, i),
                            new Position(j, i, true)
                    );
                    blockRenderables.get(j - curStartY).add(cur);
                }
            }
        }


        //Updating position for new Renderables


        this.position = newPosition;

        //FIXME check expansion in 4 directions
        //FIXME e. g. : expansion to right: (prev position + size).getblockX >= newPosition.getBlockX...
        return true;
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        for (int i = 0; i < blockRenderables.size(); i++){
            for (int j = 0; j < blockRenderables.get(i).size(); j++){
                BlockRenderable cur = blockRenderables.get(i).get(j);
                cur.render(renderer, cur.getPosition().difference(this.position));
            }
        }

        for (int i = 0; i < entityRenderables.size(); i++){
            entityRenderables.get(i).render(renderer);
        }

    }


    /**
     * {@link IRenderable}
     */
    public void render(){
        render(this.renderer);
    }

    /**
     * {@link IUpdatable}
     * FIXME might not need to call this method here
     * FIXME might need to put it only to NodeRenderable to update only those components that have actually changed
     */
    @Override
    public void update(long elapsedMillis) {
        render();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
