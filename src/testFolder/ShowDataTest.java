package testFolder;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowDataTest {
    boolean correctTestPath;
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;

    @BeforeEach
    public void setUp(){
        correctTestPath = true;
        filePath = "data/serier.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/serieforsider");
    }

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
            } catch(IOException e){
                System.out.println("Cannot find image nr." + i + "with name" + data.get(i).split(";")[0]);
            }
        }
        assertTrue(correctTestPath);
    }


    @Test
    public void loadTest() {
        try {
            data = dataHandler.loadData();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        assertEquals(data.get(0),"Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;");
        assertEquals(data.get(37),"I Love Lucy; 1951-1957; Comedy, Family; 8,3; 1-35, 2-31, 3-31, 4-30, 5-26, 6-27;");
        assertEquals(data.get(69),"The Man In The High Castle; 2015- ; Drama, Sci-fi, Thriller; 8,1; 1-10, 2-10, 3-10;");
        assertEquals(data.get(96),"Person Of Interest; 2011-2016; Action, Crime, Drama; 8,4; 1-23, 2-22, 3-23, 4-22, 5-13;");

    }
}