package testFolder;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class favoritesDataTest {
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;
    @BeforeEach
    public void setUp(){
        filePath = "data/serier.txt";
        dataHandler = new DataHandlerImpl(filePath, "data/serieforsider");
        try {
            data = dataHandler.load();
        } catch (FileNotFoundException e){
            System.out.println("No existing favorites file");
        }
    }

    @AfterEach
    public void tearDown(){
        dataHandler = null;
        data = null;
    }
    @Test
    public void saveFavoriteAddToEmptyTest(){

    }


    @Test
    public void saveFavoriteAddToExistingTest(){

    }

    @Test
    public void saveFavoriteRemove(){
        try {
            dataHandler.saveFavourite("Twin peaks");
            dataHandler.saveFavourite("Twin peaks");
        } catch (IOException e){}
    }
}
