package com.oom.game.main.entities.utils;

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

public class EntityTextures {
    private static HashMap<String, BufferedImage> list = new HashMap<>();

    /**
     * generates a HashMap of all known textures for entities
     * @throws IOException when files could not be read properly
     * FIXME this method should be called as few times as possible and before any actions with textures
     * FIXME (should be only one call in the perfect situation)
     */
    public static void generateList() throws IOException {
        //FIXME replace with File.list() method
        list.put("PlayerDefault", ImageIO.read((new File("res/entities/creatures/PlayerDefault.png")).getAbsoluteFile()));
        list.put("Wolf", ImageIO.read((new File("res/entities/creatures/Wolf.png")).getAbsoluteFile()));
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
