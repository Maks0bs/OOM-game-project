package com.oom.game.main.process;

import com.oom.game.main.entities.Creature;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.entities.mobs.Rabbit;
import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.Barrel;
import com.oom.game.main.environment.blocks.Campfire;
import com.oom.game.main.environment.blocks.Grass;
import com.oom.game.main.environment.blocks.StoneTileFloor;
import com.oom.game.main.environment.blocks.utils.BlockTextures;
import com.oom.game.main.environment.utils.Block;
import gameCore.Game;
import gameCore.Renderer;
import gameCore.eventSystem.IEventManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Process {
    /*
        FIXME add docs to Process class
        FIXME add this class to UML in a normal way (right now it's disgusting)
     */
    private Renderer defaultRenderer = null; //this might not be necessary, as all data of this renderer is included in game
    private WorldRenderable mainRenderable = null; //this might not be necessary, as all of it is included in game
    private Game game = null;
    private World world;


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



        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        World world = World.generateDefaultWorld();
        this.world = world;


        for (int i = 0; i < world.getBlockCountY(); i++){
            for (int j = 0; j < world.getBlockCountX(); j++){
                if ((i + j) % 2 == 1){
                    world.removeBlock(i, j);
                    world.addBlock(i, j, new StoneTileFloor());
                }
            }
        }


        this.mainRenderable = new WorldRenderable(
                world,
                (int) screenSize.getWidth(),
                (int) screenSize.getHeight()
        ); //FIXME replace with MainRenderable (instead of WorldRenderable)
        mainRenderable.setRenderer(defaultRenderer);


        this.game = new Game(
                "OOM GAME",
                (int) screenSize.getWidth() / 2,
                (int) screenSize.getHeight() / 2,
                30,
                mainRenderable, //renderable
                30,
                mainRenderable, //updatable
                null
        );

        world.addBlock(new Position(1, 1, true), new Barrel());

    }


    /**
     * method is responsible for executing world logic and adding stuff to it
     */
    public void run() {
        /*
            Mocking normal render process here
         */

        Block grass = this.world.getBlock(1, 1);
        for (int i = 0; i < 100; i++){
            if (i % 2 == 0){
                grass.setBlockOnTop(null);

            }
            else{
                grass.setBlockOnTop(new Barrel());

            }
            try{
                Thread.sleep(50);
                mainRenderable.updatePosition(mainRenderable.getPosition().sum(new Position(1, 0, false)));

            } catch (InterruptedException e){

            }
        }
    }

}
