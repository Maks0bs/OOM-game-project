package com.oom.game.main;

/*
    No copyright :) (yet)
 */


import com.oom.game.main.process.Process;

/*
    !!!IMPORTANT NOTES!!!
    FIXME = things that need to be added on the next several commits (quick features / fixes, urgent)
    TODO = long-term stuff
 */

/*
    TODO replace some interfaces in blocks with abstract classes to encapsulate blockOnTop/blockBelow...
    FIXME create uuid (unique id) fields for every game object to find and manipulate the easier.
    * FIXME this will be useful to delete and add entities to world
 */

/**
 * Main method for game
 * Some info on project:
 * Github repo main master: https://github.com/Maks0bs/OOM-game-project
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Dear player, welcome to OOM Process!");
        Process process = new Process();
        process.run();
    }
}
