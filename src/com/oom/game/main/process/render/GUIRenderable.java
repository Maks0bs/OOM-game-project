package com.oom.game.main.process.render;

import com.oom.game.main.environment.Position;
import com.oom.game.main.process.render.NodeRenderable;
import com.oom.game.main.ui.utils.GUITextures;
import gameCore.IRenderable;
import gameCore.Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class GUIRenderable implements IRenderable /*,IUpdatable*/ {
    public static final class MODES {
        public static final int MAIN_MENU = 1;
        public static final int IN_GAME = 2;
    }

    private int mode;
    private NodeRenderable curRenderable;

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


    public void toggle(int mode){
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

                curRenderable = new NodeRenderable(img, new Position(0, 0, true), 1, 1) {
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
                g.drawString("F = pick up, E = enchant, P = save", 0, 32);
                g.drawString("WASD to move, L to load game", 0, 64);
                g.dispose();

                curRenderable = new NodeRenderable(img, new Position(0, 9, true), 1, 1) {
                    @Override
                    protected String getNodeType() {
                        return null;
                    }
                };
                break;
            }
        }
    }

    /**
     * {@link IRenderable}
     */
    @Override
    public void render(Renderer renderer) {
        curRenderable.render(renderer);
    }
}
