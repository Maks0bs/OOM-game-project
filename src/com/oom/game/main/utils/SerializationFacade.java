package com.oom.game.main.utils;

import com.oom.game.main.entities.mobs.Wolf;
import com.oom.game.main.environment.Position;
import com.oom.game.main.environment.World;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class SerializationFacade {

    /*
        I really don't know what to save here
        I only need one world object to serialize
        Sorry :)
     */

    public SerializationFacade(){
    }

    /**
     * Saves the object to the path which was specified earlier
     */
    public void save(World world){
        /*JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(game.getParent()) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                }*/
        try {
            java.io.File file = new File("saves/gamesave.oomgp");
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file.getAbsoluteFile());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(world);
            out.close();
            fileOut.close();
            System.out.println("World data saved to /saves/gamesave.oomgp");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public World load(Container container){

        JFileChooser fileChooser = new JFileChooser();
        World world = null;
        if (fileChooser.showOpenDialog(container) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                world = (World) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return null;
            } catch (ClassNotFoundException c) {
                System.out.println("Employee class not found");
                c.printStackTrace();
                return null;
            }
        }

        return world;
    }
}
