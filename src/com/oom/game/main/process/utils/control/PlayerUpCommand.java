package com.oom.game.main.process.utils.control;

import com.oom.game.main.utils.command.GameCommand;

/**
 * This class is just an example how such commands could be implemented for all possible control actions for player.
 * If created for each possible player action, there would be too many classes. It's way easier to implement them with
 * dynamic creation of GameCommand in Process, which was done.
 */
public class PlayerUpCommand implements GameCommand {
    private PlayerControl control;




    public PlayerUpCommand(PlayerControl control){
        this.control = control;
    }

    @Override
    public void execute() {
        control.activateDirection(PlayerControl.DIRECTIONS.UP);
    }
}
