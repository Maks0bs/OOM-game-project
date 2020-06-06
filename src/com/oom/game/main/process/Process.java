package com.oom.game.main.process;

import com.oom.game.main.entities.WorldItem;
import com.oom.game.main.entities.items.Axe;
import com.oom.game.main.entities.items.Sword;
import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.Barrel;
import com.oom.game.main.environment.blocks.StoneTileFloor;
import com.oom.game.main.process.render.GUIRenderable;
import com.oom.game.main.process.render.MainRenderable;
import com.oom.game.main.process.render.WorldRenderable;
import com.oom.game.main.process.utils.control.NPCMovement;
import com.oom.game.main.process.utils.control.PlayerControl;
import com.oom.game.main.utils.GameKeyEventManager;
import com.oom.game.main.utils.GameObserver;
import gameCore.Game;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Process {
    /*
        FIXME add docs to Process class
     */
    private MainRenderable mainRenderable = null;
    private World world;
    /*
        This player is the main game entity;
        FIXME may add several players simultaneously
     */
    private Player player;

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
        Renderer defaultRenderer = new Renderer(
                (new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB))
                .getGraphics()
        );

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.world = World.generateDefaultWorld();
        this.player = Player.generateDefaultPlayer();
        world.addPlayer(player);

        for (int i = 0; i < world.getBlockCountY(); i++){
            for (int j = 0; j < world.getBlockCountX(); j++){
                if ((i + j) % 2 == 1){
                    world.removeBlock(i, j);
                    world.addBlock(i, j, new StoneTileFloor());
                }
            }
        }


        //DO NOT ADD ANY ENTITIES TO WORLD BEFORE creating mainRenderable


        GUIRenderable guiRenderable = new GUIRenderable();
        WorldRenderable worldRenderable = new WorldRenderable(
                world,
                (int) screenSize.getWidth() / 2,
                (int) screenSize.getHeight() / 2
        ); //FIXME replace with MainRenderable (instead of WorldRenderable)
        this.mainRenderable = new MainRenderable(worldRenderable, guiRenderable);
        mainRenderable.setRenderer(defaultRenderer);



        GameKeyEventManager keyEventManager = new GameKeyEventManager();
        keyEventManager.register(mainRenderable);


        //FIXME if world is smaller than the window, than adjust the window

        //Launching the game. In this class we have refs to all necessary Objects
        Game game = new Game(
                "OOM GAME",
                (int) screenSize.getWidth() / 2,
                (int) screenSize.getHeight() / 2,
                30,
                mainRenderable, //renderable
                30,
                mainRenderable, //updatable
                keyEventManager
        );



    }



    /**
     * method is responsible for executing world / game logic and adding stuff to it, like a playground
     */
    public void run() {
        /*
            Mocking normal game process here
         */
        world.addBlock(2, 1, new Barrel());
        world.addBlock(5, 5, new Barrel());

        world.removeBlock(4, 4);

        PlayerControl playerControl = new PlayerControl(mainRenderable, world, 2);
        this.player.setControl(playerControl);
        playerControl.enable();

        Wolf wolf1 = new Wolf(new Position(8, 8, true));
        world.addEntity(wolf1);

        Wolf wolf2 = new Wolf(new Position(18, 6, true));
        world.addEntity(wolf2);

        Wolf wolf3 = new Wolf(new Position(25, 6, true));
        world.addEntity(wolf3);

        WorldItem swordItem1 = new WorldItem(new Position(20, 120), "Sword", new Sword());
        world.addItem(swordItem1);
        WorldItem swordItem2 = new WorldItem(new Position(40, 140), "Sword", new Sword());
        world.addItem(swordItem2);
        WorldItem axeItem1 = new WorldItem(new Position(200, 300), "Axe", new Axe());
        world.addItem(axeItem1);
        WorldItem axeItem2 = new WorldItem(new Position(250, 450), "Axe", new Axe());
        world.addItem(axeItem2);



        NPCMovement wolfMovement = new NPCMovement(wolf1, this.world, mainRenderable.getWorldRenderable(), 0.25);
        wolf1.setMovement(wolfMovement);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        wolfMovement.enable();
                    }
                },
                2000
        );



    }



}
