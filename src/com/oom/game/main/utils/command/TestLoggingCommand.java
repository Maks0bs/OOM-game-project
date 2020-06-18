package com.oom.game.main.utils.command;

/**
 * This class is simply for testing the command pattern in GameKeyActionManager and Process
 */
public class TestLoggingCommand implements GameCommand {
    private char logSym;

    public TestLoggingCommand(char s){
        this.logSym = s;
    }

    @Override
    public void execute() {
        System.out.println("You have pressed the key \"" + logSym + "\"");
    }
}
