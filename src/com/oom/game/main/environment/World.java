package com.oom.game.main.environment;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.blocks.EmptyVoid;
import com.oom.game.main.environment.blocks.Grass;
import com.oom.game.main.environment.utils.exceptions.WorldResizeException;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.render.WorldRenderable;
import com.oom.game.main.process.utils.control.CreatureMovement;
import com.oom.game.main.utils.GameObservable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    !!!IMPORTANT NOTICE!!!
    It is not a good practice to have a lot of stacked blocks (e. g. 3 stacked barrels on a grass block)
    Even though it is possible to achieve internally, it wouldn't be visible in the GUI.
    Furthermore, after some research i have found no such 2D games that implement multi-level block stacking.
    So you can at most have 1 basic block, that you can put others on (OpenTop) and on top of this one
    !!!not more than one block!!!. Example: barrel on a grass block, but no barrels on that first barrel!
 */


/*
    Note for week 7:
    World was already developed in a facade pattern and thus can be serialized quite well
    That's why not many changes occurred to implement serialization
 */

public class World implements Serializable {
    public static final int BLOCK_SIZE = 32;
    private int blockCountX, blockCountY;
    /**
        This ArrayList contains game world's coordinates, where inner blocks are Y-coordinates
        and outer ones are X-coordinates.
        It can also be seen as a table with X- and Y-axes.
       y/x   0     1     2    ...
        0   Block Block Block
        1   Block Block Block
        2   Block Block Block
       ...
    */
    private final ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
    private final ArrayList<Entity> entities = new ArrayList<Entity>();//FIXME save entities in a Set / Map
    private final Map<String, ArrayList<WorldItem> > items = new HashMap<>();
    private final Map<Creature, CreatureMovement> movements = new HashMap<>();
    //!!! Save items only to this map. Each position contains a list of items it currently has

    /*
        At the moment there can only be one player
     */
    private Player player = null;
    private GameObservable<World> observable = new GameObservable<>();

    /**
     * @return default world (100x100), filled with 10000 grass blocks
     */
    public static World generateDefaultWorld(){
        World world = new World(100, 100);
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                world.addBlock(new Position(j, i, true), new Grass());
            }
        }

        return world;
    }

    /**
     *
     * @param blockCountY amount of blocks in the world on y-axis
     * @param blockCountX amount of blocks in the world on x-axis
     */
    public World(int blockCountY, int blockCountX){
        this.blockCountX = blockCountX;
        this.blockCountY = blockCountY;
        for (int i = 0; i < blockCountY; i++) {
            blocks.add(new ArrayList<Block>());
            for (int j = 0; j < blockCountX; j++) {
                blocks.get(i).add(new EmptyVoid()); //Default placeholder block is EmptyVoid
            }
        }
    }

    /**
     *
     * @param position position of the block the user wants to get
     * @return requested block in this world
     */
    public Block getBlock(Position position) {
        if (position.getBlockY() >= blockCountY || position.getBlockX() >= blockCountX){
            return null;
        }
        return blocks.get(position.getBlockY()).get(position.getBlockX());
    }
    public Block getBlock(int i, int j){
        return blocks.get(i).get(j);
    }

    /**
     * Change the size of the world (increase ArrayList size)
     * @param newBlockCountX obvious :)
     * @param newBlockCountY obvious :)
     * Storing old values in a temporary variable, then replacing the old value with a new one.
     * Then filling an array in a two-dimensional loop with new Blocks.
     *
     */
    public void increaseSize(int newBlockCountX, int newBlockCountY) throws WorldResizeException {
        if (newBlockCountX < this.blockCountX || newBlockCountY < this.blockCountY){
            throw new WorldResizeException("New world constraints are smaller than the previous ones");
        }
        int tempBlockCountX = this.blockCountX; 
        int tempBlockCountY = this.blockCountY;
        this.blockCountX = newBlockCountX;
        this.blockCountY = newBlockCountY;
        for (int i = tempBlockCountY; i < newBlockCountY; i++) {
            blocks.add(new ArrayList<Block>());
            for (int j = tempBlockCountX; j < newBlockCountX; j++) {
                blocks.get(i).add(new EmptyVoid()); //Default placeholder block is EmptyVoid
            }
        }

    }

    /**
     * @param position position of the block to be added
     * @param block new block to be added ON TOP of the current structure (if any).
     *              If the current structure is EmptyVoid {@link Void} then replace this block with this parameter completely
     * @return true is block was added, false if something went wrong
     */
    public boolean addBlock(Position position, Block block){
        Block curBlock = this.getBlock(position);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            //FIXME it is a bad idea to use instance of, change to smth else
            blocks.get(position.getBlockY()).set(position.getBlockX(), block);
            //TODO might need to make a deep copy HERE and in a lot of other places (where block updates occur)
            this.observable.notifyObservers(this, position, WorldRenderable.WORLD_UPDATES.ADD_BLOCK);
            return true;
        }
        else{ //otherwise there is a floor block, add one on top, but check if block on top already exists
            if (curBlock.hasBlockOnTop()){
                return false;
            }
            curBlock.setBlockOnTop(block);
            this.observable.notifyObservers(this, position, WorldRenderable.WORLD_UPDATES.ADD_BLOCK);
            return true;
        }
    }

    /**
     * @param i position of the block to be added on y-axis
     * @param j position of the block to be added on x-axis
     * @param block new block to be added ON TOP of the current structure (if any).
     *              If the current structure is EmptyVoid {@link Void} then replace this block with this parameter completely
     * @return true is block was added, false if something went wrong
     */
    public boolean addBlock(int i, int j, Block block){//FIXME improve the complexity this
        Block curBlock = this.getBlock(i, j);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            //FIXME it is a bad idea to use instance of, change to smth else
            blocks.get(i).set(j, block);
            this.observable.notifyObservers(this, new Position(j, i, true),
                    WorldRenderable.WORLD_UPDATES.ADD_BLOCK);
            return true;
        }
        else{ //otherwise there is a floor block, add one on top, but check if block on top already exists
            if (curBlock.hasBlockOnTop()){
                return false;
            }
            curBlock.setBlockOnTop(block);
            this.observable.notifyObservers(this, new Position(j, i, true),
                    WorldRenderable.WORLD_UPDATES.ADD_BLOCK);
            return true;
        }
    }

    /**
     * NOTE: deletion shouldn't leave any null pointers. Please use the EmptyVoid {@link Void} block as a placeholder.
     * @param position position of the block to be removed
     * @return true if block was removed successfully, false on error
     */
    public boolean removeBlock(Position position){//FIXME improve the complexity this
        Block curBlock = this.getBlock(position);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            return false;
        } else if (curBlock.hasBlockOnTop()){

            curBlock.setBlockOnTop(null);
            this.observable.notifyObservers(this, position, WorldRenderable.WORLD_UPDATES.REMOVE_BLOCK);
            return true;
        } else {
            blocks.get(position.getBlockY()).set(position.getBlockX(), new EmptyVoid());
            this.observable.notifyObservers(this, position, WorldRenderable.WORLD_UPDATES.REMOVE_BLOCK);
            return true;
        }
    }

    /**
     * NOTE: deletion shouldn't leave any null pointers. Please use the EmptyVoid {@link Void} block as a placeholder.
     * @param i position of the block to be removed on y-axis
     * @param j position of the block to be removed on x-axis
     * @return true if block was removed successfully, false on error
     */
    public boolean removeBlock(int i, int j){//FIXME
        Block curBlock = this.getBlock(i, j);
        if (curBlock instanceof EmptyVoid){ //if there is no block (Empty void), then replace
            return false;
        } else if (curBlock.hasBlockOnTop()){

            curBlock.setBlockOnTop(null);
            this.observable.notifyObservers(this, new Position(j, i, true),
                    WorldRenderable.WORLD_UPDATES.REMOVE_BLOCK);
            return true;
        } else {
            blocks.get(i).set(j, new EmptyVoid());
            this.observable.notifyObservers(this, new Position(j, i, true),
                    WorldRenderable.WORLD_UPDATES.REMOVE_BLOCK);
            return true;
        }
    }

    /*
        TODO In order to remove and add entities more easily
        TODO an ID field should be added to all game objects
     */
    /**
     * !!!Note!!! please do not use this method to add players. See addPlayer method;
     * @param entity the entity you want to add. Should only be added on walkable blocks!!!
     * @return true if it is legal to insert entity in current position, false otherwise
     */
    public boolean addEntity(Entity entity){ //the data of entity already has position in it, no need to pass it as argument
        Position pos = entity.getPosition();
        for (int i = pos.getBlockY(); i <= (pos.getY() + entity.getSizeY()) / BLOCK_SIZE; i++){
            for (int j = pos.getBlockX(); j <= (pos.getX() + entity.getSizeX()) / BLOCK_SIZE; j++){
                Block b = this.getBlock(i, j);
                if (b.getWalkAction() == null || !b.getWalkAction().canWalk()){
                    return false;
                }
            }
        }
        //FIXME get rid of instanceof here somehow
        if (entity instanceof Creature) {
            for (Entity e : this.entities) {
                if ((e instanceof Creature) && e.overlapsWith(entity)) {
                    return false;
                }
            }
        }


        entities.add(entity);
        this.observable.notifyObservers(this, entity, WorldRenderable.WORLD_UPDATES.ADD_ENTITY);
        return true;
    }

    /**
     * !!!ONLY USE THIS FUNCTION TO ADD ITEMS, do NOT use addEntity
     * !!!For now items cannot be moved in any way, they stay static troughout the whole game,
     * this might be changed
     * @param item item to add to world
     * @return true if item was added successfully, false otherwise
     */
    public boolean addItem(WorldItem item){
        for (int i = item.getPosition().getBlockY();
             i <= (item.getPosition().getY() + item.getSizeY()) / BLOCK_SIZE;
             i++
        ){

            for (int j = item.getPosition().getBlockX();
                 j <= (item.getPosition().getX() + item.getSizeX()) / BLOCK_SIZE;
                 j++
            ){
                Position pos = new Position(j, i, true);
                if (!items.containsKey(pos.toString())){
                    items.put(pos.toString(), new ArrayList<>());
                }

                items.get( (new Position(j, i, true)).toString()).add(item);


            }
        }

        return this.addEntity(item);
    }

    /**
     * PLEASE ONLY USE THIS FUNCTION WITH POSITION THAT ARE BLOCK POSITION (like (0, 64) or (128, 96) )
     * remove the item on top of all the others on given position
     * @param pos position, on which the item should be removed
     * @return true, if deletion was successful, false otherwise
     */
    public boolean removeItem(Position pos){
        if (items.containsKey(pos.toString())){
            ArrayList<WorldItem> itemsOnBlock = items.get(pos.toString());
            if (itemsOnBlock.size() == 0){
                return false;
            }
            WorldItem item = itemsOnBlock.get(itemsOnBlock.size() - 1);

            for (int i = item.getPosition().getBlockY();
                 i <= (item.getPosition().getY() + item.getSizeY()) / BLOCK_SIZE;
                 i++
            ){

                for (int j = item.getPosition().getBlockX();
                     j <= (item.getPosition().getX() + item.getSizeX()) / BLOCK_SIZE;
                     j++
                ){
                    Position posDel = new Position(j, i, true);
                    if (items.containsKey(posDel.toString())){
                        items.get(posDel.toString()).remove(item);
                    }
                }
            }

            return removeEntity(item);
        } else{
            return false;
        }
    }

    /**
     *
     * @param entity entity to check with what items it overlaps
     * @return list of items under the given entity
     */
    public ArrayList<WorldItem> getItemsUnderEntity(Entity entity){
        ArrayList<WorldItem> res = new ArrayList<>();
        for (int i = entity.getPosition().getBlockY();
             i <= (entity.getPosition().getY() + entity.getSizeY()) / BLOCK_SIZE;
             i++
        ){

            for (int j = entity.getPosition().getBlockX();
                 j <= (entity.getPosition().getX() + entity.getSizeX()) / BLOCK_SIZE;
                 j++
            ){
                Position pos = new Position(j, i, true);
                if (items.containsKey(pos.toString())){
                    ArrayList<WorldItem> cp = items.get(pos.toString());
                    for (int k = 0; k < cp.size(); k++){
                        WorldItem cur = cp.get(k);
                        if (!res.contains(cur) && entity.overlapsWith(cur)){
                            res.add(cur);
                        }
                    }
                }
            }
        }



        return res;
    }

    /**
     *
     * @param pos position to search items on
     * @return a list of items that can be found on the block, which is at the specified position
     */
    public ArrayList<WorldItem> getItemsByPosition(Position pos){
        return items.get(pos);
    }

    /**
     * Please use only this method to add players. Do not use addEntity for that
     * @param player player to be bound to this world
     * @return true if player was bound successfully, false otherwise
     */
    public boolean addPlayer(Player player){
        if (this.player == null){
            this.player = player;
            this.observable.notifyObservers(this, player, WorldRenderable.WORLD_UPDATES.ADD_ENTITY);
            return true;
        }
        return false;
    }

    /**
     *
     * @return true if player is bound to this world, false otherwise
     */
    public boolean hasPlayer(){
        return this.player != null;
    }

    /**
     * Unbinds player from current world
     */
    public void removePlayer(){
        this.observable.notifyObservers(this, this.player, WorldRenderable.WORLD_UPDATES.REMOVE_ENTITY);
        this.player = null;
    }

    /**
     *
     * @param position position to be tested if it is included to this world
     * @return true if position is legal, false otherwise
     */
    public boolean isValidPosition(Position position){
        return !(position.getX() < 0 || position.getX() >= blockCountX * BLOCK_SIZE ||
            position.getY() < 0 || position.getY() >= blockCountY * BLOCK_SIZE
        );
    }

    /**
     *
     * @param i y-axis block position
     * @param j x-axis block position
     * @return true if index is legal, false otherwise
     */
    public boolean isValidBlockIndex(int i, int j){
        return !(i < 0 || i >= blockCountX || j < 0 || j>= blockCountY);
    }


    /**
     *
     * @param position top left position of area to be searched in
     * @param sizeX x-size of search area rectangle
     * @param sizeY y-size of search area rectangle
     * @return {@linkplain ArrayList} of found entities
     */
    public ArrayList<Entity> getEntitiesInArea(Position position, int sizeX, int sizeY){
        //FIXME implement this method
        return null;
    }

    /**
     * FIXME might need to remove this method and do area search manually in caller classes
     * @param position top left position of area to be searched in
     * @param sizeX x-size of search area rectangle
     * @param sizeY y-size of search area rectangle
     * @return list
     */
    public ArrayList<Entity> getBlocksInArea(Position position, int sizeX, int sizeY){
        //FIXME implement this method
        return null;
    }

    /**
     *
     * @param entity the entity you want to delete from the world.
     * @return true if entity was deleted, false if it wasn't present in the list of entities or on error
     */
    public boolean removeEntity(Entity entity){
        for (int i = 0; i < entities.size(); i++){
            if (entities.get(i) == entity){//here addresses are compared, and that's what wee need, don't use .equals()
                entities.remove(i);
                this.observable.notifyObservers(this, entity, WorldRenderable.WORLD_UPDATES.REMOVE_ENTITY);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param entity entity to check
     * @return true if given entity is related to this world, false otherwise
     */
    public boolean hasEntity(Entity entity){
        return entities.contains(entity);
    }

    /**
     *
     * @param creature creature to associate with the given movement
     * @param movement movement that has to occur for given creature
     * @return true if the given creature is in the world and movement could be assigned to it, false otherwise
     */
    public boolean assignMovement(Creature creature, CreatureMovement movement){
        if (!(hasEntity(creature) || creature == player)){
            return false;
        }
        movements.put(creature, movement);
        return true;
    }

    /**
     *
     * @param creature remove movement for given creature
     */
    public void removeMovement(Creature creature){
        movements.remove(creature);
    }

    /**
     *
     * @param creature creature you want to get the movement for
     * @return movement for specified creature
     */
    public CreatureMovement getMovement(Creature creature){
        return movements.get(creature);
    }

    /**
     *
     * @return a list of creature that have been assigned a movement
     */
    public Creature[] getCreaturesWithMovement(){
        Set<Creature> set = movements.keySet();
        Creature[] result = new Creature[set.size()];
        set.toArray(result);
        return result;
    }

    public int getBlockCountX() {
        return blockCountX;
    }

    public void setBlockCountX(int blockCountX) {
        this.blockCountX = blockCountX;
    }

    public int getBlockCountY() {
        return blockCountY;
    }

    public void setBlockCountY(int blockCountY) {
        this.blockCountY = blockCountY;
    }


    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    public GameObservable<World> getObservable() {
        return observable;
    }

}
