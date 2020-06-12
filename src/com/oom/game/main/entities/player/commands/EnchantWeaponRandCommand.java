package com.oom.game.main.entities.player.commands;

import com.oom.game.main.entities.player.Player;
import com.oom.game.main.utils.GameCommand;

public class EnchantWeaponRandCommand implements GameCommand {
    private Player player;

    /**
     *
     * @param player player that wants to enchant their weapon
     */
    public EnchantWeaponRandCommand(Player player){
        this.player = player;
    }

    @Override
    public void execute() {
        player.enchantWeaponRandomly();
    }
}
