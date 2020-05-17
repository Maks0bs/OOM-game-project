package com.oom.game.main.process;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.Player;
import com.oom.game.main.entities.mobs.Rabbit;
import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.Campfire;
import com.oom.game.main.environment.blocks.Grass;
import gameCore.Game;
import gameCore.IRenderable;
import gameCore.IUpdatable;
import gameCore.Renderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Scanner;

public class Process {
    /*
        FIXME add docs to Process class
        FIXME add this class to UML in a normal way (right now it's disgusting)
     */

    /**
     * Default constructor for Process
     */
    public Process(){
        /*
            FIXME Not clear how to use renderer and Graphics, a lot of work to do,
            FIXME however i don't know what exactly should be done to make it look normal and work accordingly
         */
        BufferedImage imageGrass = null, imageBarrel = null;
        try {
            imageGrass = ImageIO.read(new File("res/Grass32px.png").getAbsoluteFile());
            imageBarrel = ImageIO.read(new File("res/Barrel32px.jpg").getAbsoluteFile());
        } catch(IOException e){
            System.out.println(1);
        }

        Renderer basicRenderer = new Renderer(imageGrass.getGraphics());
        NodeRenderable nodeRenderable = new NodeRenderable(imageGrass, 32, 32, 1, 1);
        WorldRenderer mainRenderer = new WorldRenderer();
        mainRenderer.setRenderer(basicRenderer);
        mainRenderer.addRenderable(nodeRenderable);
        WorldUpdater mainUpdater = new WorldUpdater(mainRenderer);

        Game game = new Game(
                "OOM GAME",
                1080,
                720,
                30,
                mainUpdater,
                30,
                mainRenderer
        );
        mainRenderer.addRenderable(new NodeRenderable(imageBarrel, 64, 64, 1, 1));

        //game.paintComponent();
    }

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
     * method is responsible for executing world logic and adding stuff to it
     */
    public void run() {
        World world = new World(10, 10);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                world.addBlock(new Position(j, i, true), new Grass());
            }
        }

        Player player = new Player("bruh", new Position(1, 1, true),
                World.BLOCK_SIZE + 1, World.BLOCK_SIZE + 1, 2, 2, 2 );
        Player p1 = new Player("1", new Position(2, 2, true),
                World.BLOCK_SIZE, World.BLOCK_SIZE, 1,1,1);

        world.addBlock(new Position(4, 4, true), new Campfire());
        world.addEntity(player);
        world.addEntity(p1);
        world.removeEntity(player);
        System.out.println(world.getEntities().size());

        renderWorld(world);
    }

    /**
     * TODO maybe this method should go into World class
     * @param world the world, in which you want to render the block
     * @param position position of the block to be rendered
     */
    public static void renderBlock(World world, Position position){
        //FIXME implement this method (for future)
    }

    /**
     * TODO maybe this method should go into World class
     * This method is responsible for displaying the world to the player.
     * There should be other methods for the UI and everything else
     * @param world the world you want to render / display
     */
    public static void renderWorld(World world){
        for (int i = 0; i < world.getBlockCountY(); i++){
            for (int j = 0; j < world.getBlockCountX(); j++){
                System.out.print(world.getBlock(i, j).getTexture() + " ");
            }
            System.out.print("\n");
        }
    }
}
