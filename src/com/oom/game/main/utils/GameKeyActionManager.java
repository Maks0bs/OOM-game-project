package com.oom.game.main.utils;

import gameCore.eventSystem.IEvent;
import gameCore.eventSystem.IEventListener;
import gameCore.input.keyboard.KeyPressedEvent;
import gameCore.input.keyboard.KeyReleasedEvent;

import java.util.HashMap;
import java.util.Map;

public final class GameKeyActionManager implements IEventListener {
    private static GameKeyActionManager manager = null;
    private Map<Character, GameCommand> commandsOnPress = new HashMap<>();
    private Map<Character, GameCommand> commandsOnRelease = new HashMap<>();

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
    public void setCommandOnPress(char c, GameCommand command){
        commandsOnPress.put(c, command);
    }

    /**
     *
     * @param c key character the command assigned to the stroke of which should be removed
     */
    public void removeCommandOnPress(char c){
        commandsOnPress.put(c, new NoneCommand());
    }

    /**
     *
     * @param c key character to assign the command to
     * @param command the command that is associated with the key release event on the given character key
     */
    public void setCommandsOnRelease(char c, GameCommand command){
        commandsOnRelease.put(c, command);
    }

    /**
     *
     * @param c key character the command assigned to the release event of which should be removed
     */
    public void removeCommandOnRelease(char c){
        commandsOnRelease.put(c, new NoneCommand());
    }

    /**
     * {@link IEventListener}
     */
    @Override
    public void onEvent(IEvent event) {
        //System.out.println(event);
        if (event instanceof KeyPressedEvent){
            char cur = ((KeyPressedEvent) event).getKeySymbol();
            GameCommand command = commandsOnPress.get(cur);
            if (command != null){
                command.execute();
            }
        } else if (event instanceof KeyReleasedEvent){
            char cur = ((KeyReleasedEvent) event).getKeySymbol();
            GameCommand command = commandsOnRelease.get(cur);
            if (command != null){
                command.execute();
            }
        }
    }
}
