package com.oom.game.main.ui.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/*
    TODO I don't like the idea of using this class at all
    TODO should change to some other (and better) way to get textures
    The problem is that textures shouldn't be extracted dynamically
    You should only initialize files once and then use them throughout the whole game
    as static constant values
 */

public class GUITextures {
    private static HashMap<String, BufferedImage> list = new HashMap<>();

    /**
     * generates a HashMap of all known textures for GUI
     * @throws IOException when files could not be read properly
     * FIXME this method should be called as few times as possible and before any actions with textures
     * FIXME (should be only one call in the perfect situation)
     */
    public static void generateList() throws IOException {
        //FIXME replace with File.list() method
        list.put("GuiTest", ImageIO.read((new File("res/gui/GuiTest.png")).getAbsoluteFile()));
        list.put("canvas256x32", ImageIO.read((new File("res/gui/canvas256x32.png")).getAbsoluteFile()));
        list.put("canvas512x32", ImageIO.read((new File("res/gui/canvas512x32.png")).getAbsoluteFile()));
        list.put("canvas512x128", ImageIO.read((new File("res/gui/canvas512x128.png")).getAbsoluteFile()));

    }

    /**
     *
     * @param key texture string key
     * @return correspondent texture as image
     */
    public static BufferedImage getTextureByState(String key){//FIXME add string parameter to specify dimensions (32x32 or 64x64 or other)
        return list.get(key);
    }
}
