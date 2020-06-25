package com.oom.game.main.process.render;

import com.oom.game.main.entities.Entity;
import com.oom.game.main.entities.items.Axe;
import com.oom.game.main.entities.items.Backpack;
import com.oom.game.main.entities.items.utils.InventoryComponent;
import com.oom.game.main.entities.player.Player;
import com.oom.game.main.entities.utils.EntityTextures;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.ui.utils.GUITextures;
import com.oom.game.main.utils.GameKeyActionManager;
import com.oom.game.main.utils.GameObservable;
import com.oom.game.main.utils.GameObserver;
import com.oom.game.main.utils.command.GameCommand;
import gameCore.IRenderable;
import gameCore.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class GUIRenderable implements IRenderable /*,IUpdatable*/ {
    public static final class MODES {
        public static final int MAIN_MENU = 1;
        public static final int IN_GAME = 2;
        public static final int INVENTORY = 3;
    }

    private int mode;
    private NodeRenderable guiInfo, inventoryRenderable;

    /**
     * Default constructor
     */
    public GUIRenderable() {

        toggle(MODES.MAIN_MENU);
    }


    /**
     *
     * @param bi buffered image to clone
     * @return deep copy of given buffered image
     */
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     *
     * @param src source image
     * @param ratio how many times the image should be enlarged
     * @return a very imprecise scaled image
     */
    public static BufferedImage enlargeImage(BufferedImage src, int ratio) {
        int ww = src.getWidth();
        int hh = src.getHeight();
        BufferedImage img = new BufferedImage(ww * ratio, hh * ratio, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < ww * ratio; x++) {
            for (int y = 0; y < hh * ratio; y++) {
                int col = src.getRGB(x / ratio, y / ratio);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }

    /**
     *
     * @param mode the mode to change the gui to
     */
    public void toggle(int mode){
        GameKeyActionManager manager = GameKeyActionManager.getInstance();
        if (mode != MODES.INVENTORY){
            /*
                Here we remove all commands that are related to inventory
                Deleting, adding items, and goint one level deeper
             */
            manager.removeCommandOnPress('M');
            manager.removeCommandOnPress('N');
            manager.removeCommandOnPress('B');
            for (int i = 49; i <= 57; i++){
                manager.removeCommandOnPress(i);
            }
        }
        switch(mode){
            case MODES.MAIN_MENU: {
                BufferedImage img = deepCopy(GUITextures.getTextureByState("canvas512x128"));
                Graphics g = img.createGraphics();
                Font f = new Font("Dialog", Font.PLAIN, 32);
                g.setFont(f);
                g.setColor(Color.RED);
                g.drawString("Press \"s\" to start default game", 0, 32);
                g.drawString("Press \"L\" to load game (.oomgp File)", 0, 64);
                g.dispose();

                guiInfo = new NodeRenderable(img, new Position(0, 0, true), 1, 1) {
                    @Override
                    protected String getNodeType() {
                        return null;
                    }
                };
                break;
            }
            case MODES.IN_GAME: {
                BufferedImage img = deepCopy(GUITextures.getTextureByState("canvas512x128"));
                Graphics g = img.createGraphics();
                Font f = new Font("Dialog", Font.PLAIN, 32);
                g.setFont(f);
                g.setColor(Color.RED);
                g.drawString("F = pick up, E = enchant, P = save", 4, 32);
                g.drawString("WASD to move, L to load game", 4, 64);
                g.drawString("i to open inventory", 4, 96);
                g.dispose();

                guiInfo = new NodeRenderable(img, new Position(0, 4, true), 1, 1) {
                    @Override
                    protected String getNodeType() {
                        return null;
                    }
                };
                break;
            }
            case MODES.INVENTORY: {
                BufferedImage img = deepCopy(GUITextures.getTextureByState("canvas512x128"));
                Graphics g = img.createGraphics();
                Font f = new Font("Dialog", Font.PLAIN, 24);
                g.setFont(f);
                g.setColor(Color.RED);
                g.drawString("Press \"M\" to add axe to current inventory.", 0, 24);
                g.drawString("Press \"N\" to remove first item in inventory", 0, 48);
                g.drawString("Press \"B\" to add backpack to current inventory", 0, 72);
                g.drawString("1-9 to open inventory of corresponding item", 0, 96);
                g.dispose();

                guiInfo = new NodeRenderable(img, new Position(0, 5, true), 1, 1) {
                    @Override
                    protected String getNodeType() {
                        return null;
                    }
                };
                break;
            }
        }

        this.mode = mode;
    }

    public void updateInventoryGUI(InventoryComponent component){
        int i = 0;
        BufferedImage inv = deepCopy(GUITextures.getTextureByState("canvas512x128"));
        Graphics g = inv.createGraphics();
        GameKeyActionManager manager = GameKeyActionManager.getInstance();
        manager.setCommandOnPress('M', new GameCommand() {
            @Override
            public void execute() {
                component.add(new Axe());
                updateInventoryGUI(component);
            }
        });
        manager.setCommandOnPress('B', new GameCommand() {
            @Override
            public void execute() {
                component.add(new Backpack());
                updateInventoryGUI(component);
            }
        });
        manager.setCommandOnPress('N', new GameCommand() {
            @Override
            public void execute() {
                component.remove(component.getChild(0));
                updateInventoryGUI(component);
            }
        });
        for (InventoryComponent c : component){
            BufferedImage img = enlargeImage(
                    EntityTextures.getTextureByState(c.getName()),
                    2
            );
            g.drawImage(img, i * World.BLOCK_SIZE, 0, null);

            manager.setCommandOnPress(49 + i, new GameCommand() {
                @Override
                public void execute() {
                    updateInventoryGUI(c);
                }
            });

            i++;
        }

        g.dispose();
        inventoryRenderable = new NodeRenderable(inv, new Position(0, 0, true), 1, 1) {
            @Override
            protected String getNodeType() {
                return null;
            }
        };
    }

    public int getMode() {
        return mode;
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        if (mode == MODES.INVENTORY){
            inventoryRenderable.render(renderer);
        }
        guiInfo.render(renderer);
    }
}
