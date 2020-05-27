package com.oom.game.main.process.render;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.render.BlockRenderable;
import com.oom.game.main.process.render.EntityRenderable;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
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
    private ArrayList<EntityRenderable> entityRenderables = new ArrayList<>();

    private GameObserver<Entity> playerObserver = null;
    private GameObserver<World> worldObserver = null;

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
                        new Position(j, i, true)
                );

                temp.add(cur);
            }

            blockRenderables.add(temp);
        }


        for (int i = 0; i < world.getEntities().size(); i++){
            entityRenderables.add(new EntityRenderable(world.getEntities().get(i)));
        }

        worldObserver = new GameObserver<World>() {
            @Override
            public void update(GameObservable<World> observable, World newData) {
                //FIXME implement entity changes and general block changes
            }

            @Override
            public void update(GameObservable<World> observable, World newData, Object specs) {
                //FIXME improve type check (do it without dynamic upcast)
                System.out.println("test");
                if (specs instanceof Position){
                    Position pos = (Position) specs;
                    setRenderableByPosition(pos, new BlockRenderable(newData.getBlock(pos), pos));
                    BlockRenderable br = getRenderableByPosition(pos);
                    //FIXME this is absolutely disgusting!!!!!!!!!!!
                    //FIXME change this immediately (update block on top changes here, not in block renderable
                    br.update(null, newData.getBlock(pos));
                }
            }
        };
        world.getObservable().registerObserver(worldObserver);

        if (world.hasPlayer()){
            entityRenderables.add(new EntityRenderable(world.getPlayer()));
            playerObserver = new GameObserver<>() {
                @Override
                public void update(GameObservable<Entity> observable, Entity newData) {
                    //FIXME adjust world in such a way, that the player is in the center of the screen
                    //FIXME only if player is close to boundaries, don't move screen
                    Position newPosition = new Position(newData.getPosition());
                    updatePosition(newPosition);
                }
            };

            world.getPlayer().getObservable().registerObserver(playerObserver);
        }

        //Maybe add placeholder blocks if something goes wrong with visual rendering
    }

    /**
     * This function should update the list of renderables according to the new stuff that should be displayed
     * @param newPosition new position of top left corner of rendering
     * @return true if position was updated successfully, false on problems (mainly boundaries are set wrong)
     */
    public boolean updatePosition(Position newPosition){
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
                            new Position(j, i, true)
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

            for (int i = curEndY - 1; i >= newEndY; i--){
                blockRenderables.remove(blockRenderables.size() - 1);
            }
        } else if (diffEndY < 0){
            for (int i = curEndY + 1; i <= newEndY; i++){
                ArrayList<BlockRenderable> temp = new ArrayList<>();
                for (int j = curStartX; j <= curEndX; j++){
                    BlockRenderable cur = new BlockRenderable(
                            world.getBlock(i, j),
                            new Position(j, i, true)
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
                            new Position(i, j, true)
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
                            new Position(i, j, true)
                    );
                    blockRenderables.get(j - curStartY).add(cur);
                }
            }
        }


        //Updating position for new Renderables


        this.position = newPosition;
        return true;
    }

    /**
     * FIXME add this method to UML
     * @param dx change of position by x-axis
     * @param dy change of position by y-axis
     * @return true if moving position was successful and false otherwise
     */
    public boolean movePosition(int dx, int dy){
        Position newPosition = new Position(this.position.getX() + dx, this.position.getY() + dy);
        return this.updatePosition(newPosition);
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
                //FIXME render entities depending on their position to render them behind blocks on top of other blocks!!!!
            }
        }


        for (int i = 0; i < entityRenderables.size(); i++){
            EntityRenderable cur = entityRenderables.get(i);
            cur.render(renderer, cur.getPosition().difference(this.position));
        }

    }

    /**
     *
     * @param position position of renderable the caller wants to receive
     * @return the wanted BlockRenderable
     */
    public BlockRenderable getRenderableByPosition(Position position){
        //FIXME check if position is in bounds of current WorldRenderable
        Position relative = position.difference(this.position);
        return blockRenderables.get(relative.getBlockY()).get(relative.getBlockX());
    }

    /**
     *
     * @param position position where to set the renderable (relative to the world, NOT to the renderable)
     * @param br the Renderable that is to be set
     * @return true if change was successful, false otherwise
     */
    public boolean setRenderableByPosition(Position position, BlockRenderable br){
        Position relative = position.difference(this.position);
        blockRenderables.get(relative.getBlockY()).set(relative.getBlockX(), br);

        return true;
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

    public GameObserver<Entity> getPlayerObserver() {
        return playerObserver;
    }
}