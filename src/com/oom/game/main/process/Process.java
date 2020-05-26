package com.oom.game.main.process;

import com.oom.game.main.entities.player.Player;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.environment.blocks.Barrel;
import com.oom.game.main.environment.blocks.StoneTileFloor;
import com.oom.game.main.environment.utils.Block;
import com.oom.game.main.process.utils.PlayerControl;
import com.oom.game.main.utils.GameKeyEventManager;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
import gameCore.Game;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Process {
    /*
        FIXME add docs to Process class
        FIXME add this class to UML in a normal way (right now it's disgusting)
     */
    private Renderer defaultRenderer = null; //this might not be necessary, as all data of this renderer is included in game
    private MainRenderable mainRenderable = null; //this might not be necessary, as all of it is included in game
    private Game game = null;
    private World world;
    private GameKeyEventManager keyEventManager = null;
    /*
        This player is the main game entity;
        FIXME may add several players simultaneously
     */
    private Player player;
    private ArrayList<GameObserver<IRenderable> > observers = new ArrayList<GameObserver<IRenderable>>();


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

        GUIRenderable guiRenderable = new GUIRenderable();
        WorldRenderable worldRenderable = new WorldRenderable(
                world,
                (int) screenSize.getWidth() / 2,
                (int) screenSize.getHeight() / 2
        ); //FIXME replace with MainRenderable (instead of WorldRenderable)
        this.mainRenderable = new MainRenderable(worldRenderable, guiRenderable);
        mainRenderable.setRenderer(defaultRenderer);



        this.keyEventManager = new GameKeyEventManager();
        keyEventManager.register(mainRenderable);


        this.game = new Game(
                "OOM GAME",
                (int) screenSize.getWidth() / 2,
                (int) screenSize.getHeight() / 2,
                30,
                mainRenderable, //renderable
                30,
                mainRenderable, //updatable
                keyEventManager
        );

        world.addBlock(new Position(1, 1, true), new Barrel());

    }


    /**
     * method is responsible for executing world / game logic and adding stuff to it
     */
    public void run() {
        /*
            Mocking normal game process here
         */

        PlayerControl playerControl = new PlayerControl(mainRenderable, player, world);
        playerControl.enable();

    }



}
