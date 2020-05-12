package com.oom.game.main.process;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Player;
import com.oom.game.main.entities.mobs.Rabbit;
import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.Grass;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    /*
        FIXME add docs to Game class
        FIXME add this class to UML in a normal way (right now it's disgusting)
     */

    /**
     * TODO improve random generation
     * @return random creature (currently 2/3 chance = rabbit, 1/3 chance = wolf)
     */
    private static Creature generateRandomCreature(){
        int key = (int) (Math.random() * 100);
        if (key <= 66){
            return new Rabbit(new Position(0, 0, true));
        } else { //here new possible creatures can be added
            return new Wolf(new Position(0, 0, true));
        }
    }


    /**
     * FIXME add description to this method
     * method is responsible for executing world logic and adding stuff to it
     */
    public static void run() {
        World world = new World(10, 10);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                world.addBlock(new Position(j, i, true), new Grass());
            }
        }

        Player player = new Player("bruh", new Position(1, 1, false),
                World.BLOCK_SIZE + 5, World.BLOCK_SIZE + 5, 2, 2, 2 );
        Player p1 = new Player("1", new Position(2, 2, true),
                64, 64, 1,1,1);
        world.addEntity(player);
        world.addEntity(p1);
        System.out.println(world.getEntities().size());

        for (int i = 0; i < world.getBlockCountY(); i++){
            for (int j = 0; j < world.getBlockCountX(); j++){
                world.getBlock(i, j).display();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
