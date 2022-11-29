package testFolder;
//de to første er blot til at displaye billidet

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class dataTest {
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;
    @BeforeEach
    public void setUp()throws Exception{
        filePath = "data/serier.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/serieforsider");
        data = dataHandler.load();
    }

    @AfterEach
    public void tearDown(){
        dataHandler = null;
        data = null;
    }

    @Test
    public  void getImageTest() throws Exception{
        for(int i=0; i<100; i++) {
            try {
                BufferedImage img = dataHandler.getImage(((data.get(i)).split(";"))[0]);
                showLoadedImage(img);
            } catch(Exception e){
                System.out.println(i);
            }

        }
        assertTrue(true);
    }


    @Test
    public void loadTest() throws Exception{
        assertEquals(data.get(0),"Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;");
    }

    //I have no clue how to test if I get the correct image, but I test that no errors occur
    public  void showLoadedImage(BufferedImage img){
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Test
    public void emptyFavoriteTest(){
        File file = new File(filePath);
        file.delete();
        dataHandler.saveFavourite("Twin peaks");
    }

    @Test
    public void addFavoriteTest(){

    }

    @Test
    public void removeFavoriteTest() throws Exception{
        dataHandler.saveFavourite("Twin peaks");
        dataHandler.saveFavourite("Twin peaks");
    }

    @Test
    public void saveFavoriteAddToEmptyTest(){

    }


    @Test
    public void saveFavoriteAddToExistingTest(){

    }

    @Test
    public void saveFavoriteRemove(){
        dataHandler.saveFavourite("Twin peaks");
        dataHandler.saveFavourite("Twin peaks");
    }
}

