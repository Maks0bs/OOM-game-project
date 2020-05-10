package com.oom.game.main.process;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Player;
import com.oom.game.main.entities.mobs.Rabbit;
import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.environment.Position;

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
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(
                "Player1",
                new Position(0, 0, true),
                10,
                0,
                0
        );
        ArrayList<Creature> mobs = new ArrayList<Creature>();
        mobs.add(generateRandomCreature());
        mobs.add(generateRandomCreature());

        while(player.isAlive()){
            System.out.println(player.getInfo() + "\n");
            System.out.println("Creatures around: ");
            for (int i = 0; i < mobs.size(); i++) {
                System.out.println(i + ":\n" + mobs.get(i).getInfo());
            }
            System.out.println("Enter the number of the creature to attack: ");
            int num = scanner.nextInt();
            player.attack(mobs.get(num));
            if (!mobs.get(num).isAlive()){
                mobs.set(num, generateRandomCreature());
            }
        }

        System.out.println("GAME OVER");
    }
}
