package com.oom.game.main.environment.blocks.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
    TODO I don't like the idea of using this class at all
    TODO should change to some other (and better) way to get textures
    The problem is that textures shouldn't be extracted dynamically
    You should only initialize files once and then use them throughout the whole game
    as static constant values
 */

public class BlockTextures {
    private static HashMap<String, BufferedImage> list = new HashMap<>();

    /**
     * generates a HashMap of all known textures for blocks
     * @throws IOException when files could not be read properly
     * FIXME this method should be called as few times as possible and before any actions with textures
     * FIXME (should be only one call in the perfect situation)
     */
    public static void generateList() throws IOException {
        //FIXME replace with File.list() method
        list.put("Barrel", ImageIO.read((new File("res/blocks/32px/Barrel.png")).getAbsoluteFile()));
        list.put("BarrelOpen", ImageIO.read((new File("res/blocks/32px/BarrelOpen.png")).getAbsoluteFile()));
        list.put("Grass", ImageIO.read((new File("res/blocks/32px/Grass.png")).getAbsoluteFile()));
        list.put("EmptyVoid", ImageIO.read((new File("res/blocks/32px/EmptyVoid.png")).getAbsoluteFile()));
        list.put("StoneTileFloor", ImageIO.read((new File("res/blocks/32px/StoneTileFloor.png")).getAbsoluteFile()));
    }

    /**
     *
     * @param key texture string key
     * @return correspondent texture as image
     */
    public static BufferedImage getTextureByAppearance(String key){//FIXME add string parameter to specify dimensions (32x32 or 64x64 or other)
        return list.get(key);
    }
}
