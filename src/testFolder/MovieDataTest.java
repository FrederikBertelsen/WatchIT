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
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MovieDataTest {
    boolean correctTestPath;
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;

    @BeforeEach
    public void setUp(){
        correctTestPath = true;
        filePath = "data/film.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/filmplakater");
    }

    @AfterEach
    public void tearDown(){
        assertTrue(correctTestPath);
        dataHandler = null;
        data = null;
        filePath = null;
    }

    //Det kan på dette stadie ikke testes om det korrekte billide loades men vi tester at et blillede kan loades for alle titler
    @Test
    public  void getImageTest(){
        try {
            data = dataHandler.loadData();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            correctTestPath = false;
        }
        for(int i=0; i<100; i++) {
            try {
                BufferedImage img = dataHandler.getImage(((data.get(i)).split(";"))[0]);
                //showLoadedImage(img);
            } catch(IOException e){
                System.out.println("Cannot find image nr." + i + "with name" + data.get(i).split(";")[0]);
                correctTestPath = false;
            }
        }
    }


    @Test
    public void loadTest() {
        try {
            data = dataHandler.loadData();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            correctTestPath = false;
        }
        assertEquals(data.get(0),"The Godfather; 1972; Crime, Drama; 9,2; ");
        assertEquals(data.get(2),"Schindler's List; 1993; Biography, Drama, History; 8,9;");
        assertEquals(data.get(17),"12 Angry Men; 1957; Crime, Drama; 8,9;");
        assertEquals(data.get(53),"The Good, The Bad And The Ugly; 1966; Western; 8,9;");
        assertEquals(data.get(72),"Rain Man; 1988; Drama; 8,0;");
    }
    //Denne kode blev brugt til at teste at nogle få titler om de returnerede det korrekte forside
    public  void showLoadedImage(BufferedImage img){
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}