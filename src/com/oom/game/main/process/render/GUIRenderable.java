package com.oom.game.main.process.render;

import com.oom.game.main.process.Process;
import com.oom.game.main.entities.items.Axe;
import com.oom.game.main.entities.items.Backpack;
import com.oom.game.main.entities.items.utils.InventoryComponent;
import com.oom.game.main.entities.utils.EntityTextures;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;
import com.oom.game.main.ui.utils.GUITextures;
import com.oom.game.main.utils.GameKeyActionManager;
import com.oom.game.main.utils.SerializationFacade;
import com.oom.game.main.utils.command.GameCommand;
import gameCore.IRenderable;
import gameCore.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class GUIRenderable implements IRenderable /*,IUpdatable*/ {
    public static final class MODES {
        public static final int MAIN_MENU = 1;
        public static final int IN_GAME = 2;
        public static final int INVENTORY = 3;
        public static final int HELP = 4;
        public static final int IN_GAME_MENU = 5;
    }

    private int mode;
    private NodeRenderable guiInfo, inventoryRenderable;
    private JComponent mainComponent = null;
    private JComponent outMenuComponent;
    private JComponent inMenuComponent;
    private Process process;

    /**
     * Default constructor
     */
    public GUIRenderable(JComponent mainComponent, Process process) {
        this.process = process;
        this.mainComponent = mainComponent;
        initComponent();

        toggle(MODES.MAIN_MENU);
    }

    private void initComponent(){
        SerializationFacade sf = new SerializationFacade();
        outMenuComponent = new JPanel();
        outMenuComponent.setLayout(new GridLayout(4, 1, 20, 20));
        outMenuComponent.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        outMenuComponent.add(new JLabel("Starting menu [press ESC to enter menu in game]", SwingConstants.CENTER));
        JButton button1 = new JButton("Start new game");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                process.run();
                mainComponent.getParent().getParent().getParent().getParent().requestFocus();
                //TODO very disguisting solution, have no idea how to fix focus otherwise
                mainComponent.revalidate();
            }
        });
        outMenuComponent.add(button1);
        JButton button2 = new JButton("Load game");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                World w = sf.load(mainComponent.getParent());
                if (w != null){
                    process.run(w);
                }
            }
        });
        outMenuComponent.add(button2);
        JButton button3 = new JButton("Quit");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                process.stop();
            }
        });
        outMenuComponent.add(button3);


        inMenuComponent = new JPanel();
        inMenuComponent.setLayout(new GridLayout(5, 1, 20, 20));
        inMenuComponent.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        inMenuComponent.add(new JLabel("In-game menu", SwingConstants.CENTER));
        JButton button4 = new JButton("Back to game");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainComponent.getParent().getParent().getParent().getParent().requestFocus();
                //TODO very disguisting solution, have no idea how to fix focus otherwise
                toggle(MODES.IN_GAME);
            }
        });
        inMenuComponent.add(button4);
        JButton button5 = new JButton("Load another game");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                World w = sf.load(mainComponent.getParent());
                if (w != null){
                    process.run(w);
                }
            }
        });
        inMenuComponent.add(button5);
        JButton button6 = new JButton("Show help in-game");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggle(MODES.HELP);
            }
        });
        inMenuComponent.add(button6);
        JButton button7 = new JButton("Back to main menu");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggle(MODES.MAIN_MENU);
            }
        });
        inMenuComponent.add(button7);

        mainComponent.setLayout(new GridLayout(1, 1));
        mainComponent.getParent().revalidate();
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


        this.mode = mode;

        mainComponent.getParent().getParent().getParent().getParent().requestFocus();
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

            case MODES.IN_GAME_MENU:{

                mainComponent.removeAll();
                mainComponent.add(inMenuComponent);
                mainComponent.setVisible(true);
                mainComponent.revalidate();
                break;
            }
            case MODES.MAIN_MENU: {

                mainComponent.removeAll();
                mainComponent.add(outMenuComponent);
                mainComponent.setVisible(true);
                mainComponent.revalidate();
//                BufferedImage img = deepCopy(GUITextures.getTextureByAppearance("canvas512x128"));
//                Graphics g = img.createGraphics();
//                Font f = new Font("Dialog", Font.PLAIN, 32);
//                g.setFont(f);
//                g.setColor(Color.RED);
//                g.drawString("Press \"s\" to start default game", 0, 32);
//                g.drawString("Press \"L\" to load game (.oomgp File)", 0, 64);
//                g.dispose();
//
//                guiInfo = new NodeRenderable(img, new Position(0, 0, true), 1, 1) {
//                    @Override
//                    protected String getNodeType() {
//                        return null;
//                    }
//                };
                break;
            }
            case MODES.IN_GAME: {
                mainComponent.removeAll();
                mainComponent.setVisible(true);
                mainComponent.revalidate();
                process.showRenderables();
//                BufferedImage img = deepCopy(GUITextures.getTextureByAppearance("canvas512x128"));
//                Graphics g = img.createGraphics();
//                Font f = new Font("Dialog", Font.PLAIN, 32);
//                g.setFont(f);
//                g.setColor(Color.RED);
//                g.drawString("F = pick up, E = enchant, P = save", 4, 32);
//                g.drawString("WASD to move, L to load game", 4, 64);
//                g.drawString("i to open inventory", 4, 96);
//                g.dispose();
//
//                guiInfo = new NodeRenderable(img, new Position(0, 4, true), 1, 1) {
//                    @Override
//                    protected String getNodeType() {
//                        return null;
//                    }
//                };
                break;
            }
            case MODES.HELP: {
                if (guiInfo != null){
                    guiInfo = null;
                    toggle(MODES.IN_GAME);
                    break;
                }
                BufferedImage img = deepCopy(GUITextures.getTextureByAppearance("canvas512x512"));
                Graphics g = img.createGraphics();
                Font f = new Font("Dialog", Font.PLAIN, 32);
                g.setFont(f);
                g.setColor(Color.BLACK);
                g.drawString("F = pick up, E = enchant, P = save", 4, 32);
                g.drawString("WASD to move, L to load game", 4, 64);
                g.drawString("i to open inventory", 4, 96);
                g.drawString("Press \"M\" to add axe to current inventory.", 4, 120);
                g.drawString("Press \"N\" to remove first item in inventory", 4, 144);
                g.drawString("Press \"B\" to add backpack to current inventory", 4, 168);
                g.drawString("1-9 to open inventory of corresponding item", 4, 192);
                g.dispose();

                guiInfo = new NodeRenderable(img, new Position(0, 4, true), 1, 1) {
                    @Override
                    protected String getNodeType() {
                        return null;
                    }
                };

                toggle(MODES.IN_GAME);
                break;
            }
            case MODES.INVENTORY: {
//                BufferedImage img = deepCopy(GUITextures.getTextureByAppearance("canvas512x128"));
//                Graphics g = img.createGraphics();
//                Font f = new Font("Dialog", Font.PLAIN, 24);
//                g.setFont(f);
//                g.setColor(Color.RED);
//                g.drawString("Press \"M\" to add axe to current inventory.", 0, 24);
//                g.drawString("Press \"N\" to remove first item in inventory", 0, 48);
//                g.drawString("Press \"B\" to add backpack to current inventory", 0, 72);
//                g.drawString("1-9 to open inventory of corresponding item", 0, 96);
//                g.dispose();
//
//                guiInfo = new NodeRenderable(img, new Position(0, 5, true), 1, 1) {
//                    @Override
//                    protected String getNodeType() {
//                        return null;
//                    }
//                };
                break;
            }
        }

    }

    public void updateInventoryGUI(InventoryComponent component){
        int i = 0;
        BufferedImage inv = deepCopy(GUITextures.getTextureByAppearance("canvas512x128"));
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
                    EntityTextures.getTextureByAppearance(c.getName()),
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
        if (mainComponent != null){
            mainComponent.setVisible(true);
            mainComponent.paintComponents(renderer.getGraphics());
        }
        if (guiInfo != null){
            guiInfo.render(renderer);
        }
    }

    public JComponent getMainComponent() {
        return mainComponent;
    }

    public void setMainComponent(JComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}
