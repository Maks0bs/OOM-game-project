package com.oom.game.main;

/*
    No copyright :) (yet)
 */

import com.oom.game.main.process.Game;

/**
    !!!IMPORTANT NOTES!!!
    FIXME = things that need to be added on the next several commits (quick features / fixes, urgent)
    TODO = long-term stuff
 */

/*
    FIXME replace some interfaces in blocks with abstract classes to encapsulate blockOnTop/blockBelow...
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Dear player, welcome to OOM Game!");
        Game.run();

    }
}
