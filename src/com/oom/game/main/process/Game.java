package com.oom.game.main.process;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Player;
import com.oom.game.main.entities.Rabbit;
import com.oom.game.main.entities.Wolf;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    /*
        At the moment this is very inaccurate
        TODO improve random generation
        2/3 chance to generate a rabbit,
        1/3 chance to generate a wolf
     */
    private static Creature generateRandomCreature(){
        int key = (int) (Math.random() * 100);
        if (key <= 66){
            return new Rabbit();
        } else { //here new possible creatures can be added
            return new Wolf();
        }
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player("Player1", 100, 10, 0);
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
