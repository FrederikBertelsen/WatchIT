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

public class FavoritesDataTest {
    boolean correctTestPath;
    DataHandler dataHandler;
    ArrayList<String> data;
    String filePath;
    @BeforeEach
    public void setUp(){
        correctTestPath = true;
        filePath = "data/favorites.txt";
        dataHandler = new DataHandlerImpl(filePath, true);
        try {
            data = dataHandler.loadData();
        } catch (FileNotFoundException e){
            System.out.println("No existing favorites file");
        }
    }

    @AfterEach
    public void tearDown(){
        assertTrue(correctTestPath);
        dataHandler = null;
        data = null;
    }

    @Test
    public void saveFavoriteAddToEmptyTest(){
        File file = new File(filePath);
        if (file.exists() && !file.delete()){
            correctTestPath = false;
        }
        try {
            dataHandler.addToFile("Rear Window");
            data = dataHandler.loadData();
        } catch (IOException e){
            correctTestPath = false;
        } catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
            correctTestPath = false;
        }
        try{
            //tester om der er flere elementer end det nyligt tilføjgede i filen
            data.get(1);
            correctTestPath = false;
        }catch (IndexOutOfBoundsException e) {
            //her skal exceptionen være kastet så dette er den korrekte path
        }
        assertEquals(data.get(0),"Rear Window");
    }

    @Test
    public void saveFavoriteAddToExistingTest() {
        if (!new File(filePath).exists()){
            System.out.println("no existing file so save to existing doesn't really work");
        }
        try {
            dataHandler.addToFile("The Maltese Falcon");
            dataHandler.addToFile("Homeland");
        } catch (IOException e){
            correctTestPath = false;
        }
        catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
            correctTestPath = false;
        }
    }

    @Test
    public void saveFavoriteRemoveTest(){
        try {
            dataHandler.addToFile("Twin peaks");
        } catch (IOException e){
            correctTestPath = false;
        } catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
            correctTestPath = false;
        }
        try {
            dataHandler.removeFromFile("Twin peaks");
        } catch (IOException e){
            correctTestPath = false;
        } catch (FavoriteAddRemoveException e){
            System.out.println(e.getMessage());
            correctTestPath = false;
        }
        try {
            dataHandler.removeFromFile("NonExistent title");
            correctTestPath = false;
        } catch (IOException e){
            correctTestPath = false;
        } catch (FavoriteAddRemoveException e){
            //her skal exceptionen være kastet så dette er den korrekte path
        }
    }
}