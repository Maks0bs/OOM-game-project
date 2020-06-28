package com.oom.game.main.utils;

import com.oom.game.main.utils.command.GameCommand;
import com.oom.game.main.utils.command.NoneCommand;
import gameCore.eventSystem.IEvent;
import gameCore.eventSystem.IEventListener;
import gameCore.input.keyboard.KeyPressedEvent;
import gameCore.input.keyboard.KeyReleasedEvent;

import java.util.HashMap;
import java.util.Map;

public final class GameKeyActionManager implements IEventListener {
    private static GameKeyActionManager manager = null;
    private final Map<Integer, GameCommand> commandsOnPress = new HashMap<>();
    private final Map<Integer, GameCommand> commandsOnRelease = new HashMap<>();

    /**
     * Default private constructor for singleton to function
     */
    private GameKeyActionManager() {
        SystemKeyEventManager.getInstance().register(this);
    }

    /**
     *
     * @return the only instance of system key actions manager [singleton]
     */
    public static GameKeyActionManager getInstance() {
        if (manager == null){
            manager = new GameKeyActionManager();
        }
        return manager;
    }

    /**
     *
     * @param c key character to assign the command to
     * @param command the command that is associated with the key stroke on the given character key
     */
    public void setCommandOnPress(int c, GameCommand command){
        commandsOnPress.put(c, command);
    }

    /**
     *
     * @param c key character the command assigned to the stroke of which should be removed
     */
    public void removeCommandOnPress(int c){
        commandsOnPress.put(c, new NoneCommand());
    }

    /**
     *
     * @param c key character to assign the command to
     * @param command the command that is associated with the key release event on the given character key
     */
    public void setCommandsOnRelease(int c, GameCommand command){
        commandsOnRelease.put(c, command);
    }

    /**
     *
     * @param c key character the command assigned to the release event of which should be removed
     */
    public void removeCommandOnRelease(int c){
        commandsOnRelease.put(c, new NoneCommand());
    }

    /**
     * {@link IEventListener}
     */
    @Override
    public void onEvent(IEvent event) {
        if (event instanceof KeyPressedEvent){
            int cur = ((KeyPressedEvent) event).getKeyCode();
            GameCommand command = commandsOnPress.get(cur);
            if (command != null){
                command.execute();
            }
        } else if (event instanceof KeyReleasedEvent){
            int cur = ((KeyReleasedEvent) event).getKeyCode();
            GameCommand command = commandsOnRelease.get(cur);
            if (command != null){
                command.execute();
            }
        }
    }
}
