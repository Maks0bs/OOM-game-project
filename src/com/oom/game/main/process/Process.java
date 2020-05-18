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
import gameCore.Renderer;
import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

public class Process {
    /*
        FIXME add docs to Process class
        FIXME add this class to UML in a normal way (right now it's disgusting)
     */
    private Renderer defaultRenderer = null; //this might not be necessary, as all data of this renderer is included in game
    private WorldRenderable mainRenderable = null; //this might not be necessary, as all of it is included in game
    private Game game = null;

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
     * FIXME maybe create constructor with custom values (graphics / renderer / game)
     * Default constructor for Process
     */
    public Process(){
        /*
            FIXME Not clear how to use renderer and Graphics, a lot of work to do,
            FIXME however i don't know what exactly should be done to make it look normal and work accordingly
            FIXME upd 18.05.2020: Basic BufferedImage graphics. This approach is still quite disguisting,
            FIXME but at least there is not NullPointerException
         */
        this.defaultRenderer = new Renderer(
                (new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB))
                .getGraphics()
        );

        this.mainRenderable = new WorldRenderable();
        mainRenderable.setRenderer(defaultRenderer);

        this.game = new Game(
                "OOM GAME",
                1080,
                720,
                30,
                mainRenderable,
                30,
                mainRenderable
        );



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

        /*
            Mocking normal render process here
         */
        BufferedImage imageGrass = null, imageBarrel = null;
        try {
            imageGrass = ImageIO.read(new File("res/Grass32px.png").getAbsoluteFile());
            imageBarrel = ImageIO.read(new File("res/Barrel32px.jpg").getAbsoluteFile());
        } catch(IOException e){
            System.out.println(1);
        }

        mainRenderable.addRenderable(new NodeRenderable(
                imageGrass,
                new Position(32, 32, false),
                1, 1
        ));
        NodeRenderable barrelNode = new NodeRenderable(
                imageBarrel,
                new Position (64, 64, false),
                1, 1
        );
        mainRenderable.addRenderable(barrelNode);

        for (int i = 0; i < 1000; i++){
            if(i % 2 == 0) {
                Position pos = barrelNode.getPosition();
                pos.setX(pos.getX() + 4);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            else {
                Position pos = barrelNode.getPosition();
                pos.setX(pos.getX() -2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
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
