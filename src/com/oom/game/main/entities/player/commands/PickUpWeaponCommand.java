package com.oom.game.main.entities.player.commands;

import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.World;
import com.oom.game.main.utils.GameCommand;

import java.util.ArrayList;

public class PickUpWeaponCommand implements GameCommand {
    private World world = null;
    private Player player = null;

    /**
     *
     * @param world world to pick up the weapon in
     * @param player player that picks up the weapon
     */
    public PickUpWeaponCommand(World world, Player player){
        this.world = world;
        this.player = player;
    }

    /**
     *
     * @param world world with the player that picks up the weapon
     */
    public PickUpWeaponCommand(World world){
        this.world = world;
        this.player = world.getPlayer();
    }

    @Override
    public void execute() {
        ArrayList<WorldItem> items = world.getItemsUnderEntity(player);
        if (items == null || items.size() == 0){
            return;
        }

        WorldItem pickedUp = items.get(0);
        world.removeItem(pickedUp.getPosition().getBlockPosition());

        player.pickUpWeapon(pickedUp);
    }
}
