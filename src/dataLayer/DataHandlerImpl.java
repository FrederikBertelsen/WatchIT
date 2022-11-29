package dataLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class DataHandlerImpl implements DataHandler {

    private final String filePath;
    private final String imageFolderPath;

    public DataHandlerImpl(String filePath) {
        this.filePath = filePath;
        this.imageFolderPath = null;
    }

    public DataHandlerImpl(String filePath, String imageFolderPath) {
        this.filePath = filePath;
        this.imageFolderPath = imageFolderPath;
    }

    public BufferedImage getImage(String mediaName) throws IOException {
        String path = imageFolderPath + "/" + mediaName + ".jpg";
        BufferedImage result = ImageIO.read(new File(path));
        return result;
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        //imports the file to read from and creates a Scanner that reads from this file
        File file = new File(filePath);
        Scanner s = new Scanner(file);

        while (s.hasNextLine()) {
            result.add(s.nextLine());
        }

        s.close();
        return result;
    }

    //jeg har virkelig lyst til at hardcode den her fordi den kunne meget let slette alle vores filmdata
    public void saveFavourite(String data) throws IOException{
        //her laver vi den fil som bliver til den nye "favorites.txt" fil
        File newFile = new File("./tempFile.txt");
        //for at sørge for at vi ikke skriver til en existerende fil fjerner vi filer ved navn "tempFile.txt" før vi forsøger at skabe den
        if (!newFile.exists()) newFile.delete();
        newFile.createNewFile();
        //initialiserer vores writer for at kunne skrive til filen
        PrintWriter pw = new PrintWriter(newFile);
        boolean exists = false;
        //tjekker igennem alle medier allerede på favorites listen og hvis mediet er der i forvejen skriver vi det ikke til ny fil og vi noterer at det var der
        try{
            for (String s:load()){
                if(data.equals(s)){
                    exists = true;
                }
                else{
                    pw.println(s);
                }
            }
        } catch (FileNotFoundException e){

        }
        //hvis ikke vi har set mediet endnu skrives det til filen
        if(!exists){
            pw.println(data);
        }
        pw.close();
        File favorites = new File(filePath);
        //replaces the old file with the new
        favorites.delete();
        newFile.renameTo(favorites);
    }
}
