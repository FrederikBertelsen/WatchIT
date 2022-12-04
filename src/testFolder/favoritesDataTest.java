package testFolder;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import dataLayer.FavoriteAddRemoveException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class favoritesDataTest {
    boolean correctTestPath;
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;
    @BeforeEach
    public void setUp(){
        filePath = "data/favorits.txt";
        dataHandler = new DataHandlerImpl(filePath);
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
        File file = new File(filePath);
        file.delete();
        try {
            dataHandler.addFavorite("Rear Window");
            data = dataHandler.load();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
        }
        assertEquals(data.get(0),"Rear Window");
        try{
            data.get(1);
            correctTestPath = false;
        }catch (IndexOutOfBoundsException e) {
            correctTestPath = true;
        }
        assertTrue(correctTestPath);
    }

    @Test
    public void saveFavoriteAddToExistingTest() {
        if(!new File(filePath).exists()) System.out.println("no existing file so save to existing doesn't really work");
        try {
            dataHandler.addFavorite("The Maltese Falcon");
            dataHandler.addFavorite("Homeland");
        } catch (IOException e){}
        catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void saveFavoriteRemoveTest(){

        try {
            dataHandler.addFavorite("Twin peaks");
            dataHandler.removeFavorite("Twin peaks");
            dataHandler.removeFavorite("Rear Window");
        } catch (IOException e){}
        catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
        }
    }
}