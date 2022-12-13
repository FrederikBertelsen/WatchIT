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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class MediaDataTest {
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;
    @BeforeEach
    public void setUp(){
        filePath = "data/serier.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/serieforsider");
    }

    /*@BeforeEach
    public void setUp(){
        filePath = "data/film.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/filmplakater");
    }*/

    @AfterEach
    public void tearDown(){
        dataHandler = null;
        data = null;
        filePath = null;
    }

    @Test
    public  void getImageTest(){
        try {
            data = dataHandler.loadData();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        for(int i=0; i<100; i++) {
            try {
                BufferedImage img = dataHandler.getImage(((data.get(i)).split(";"))[0]);
                //showLoadedImage(img);
            } catch(IOException e){
                System.out.println("Cannot find image nr." + i + "with name" + data.get(i).split(";")[0]);
            }

        }
    }


    @Test
    public void loadMovieTest() {
        filePath = "data/film.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/filmplakater");
        try {
            data = dataHandler.loadData();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        assertEquals(data.get(0),"The Godfather; 1972; Crime, Drama; 9,2; ");
        assertEquals(data.get(53),"The Good, The Bad And The Ugly; 1966; Western; 8,9;");
    }

    @Test
    public void loadShowTest() {
        filePath = "data/serier.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/serieforsider");
        try {
            data = dataHandler.loadData();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        assertEquals(data.get(0),"Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;");
        assertEquals(data.get(53),"South Park; 1997-; Animation, Comedy; 8,7; 1-13, 2-18, 3-17, 4-17, 5-14, 6-17, 7-15, 8-14, 9-14, 10-14, 11-14, 12-14, 13-14, 14-14, 15-14, 16-14, 17-10, 18-10, 19-10, 20-10, 21-10, 22-10;");
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
}