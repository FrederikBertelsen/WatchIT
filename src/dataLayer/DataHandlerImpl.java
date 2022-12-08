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
        Scanner s = new Scanner(file, "ISO-8859-1");

        while (s.hasNextLine()) {
            result.add(s.nextLine());
        }

        s.close();
        return result;
    }

    //jeg har virkelig lyst til at hardcode den her fordi den kunne meget let slette alle vores filmdata
    public void removeFavorite(String data) throws FavoriteAddRemoveException,IOException{
        //her laver vi den fil som bliver til den nye "favorites.txt" fil
        File newFile = new File("./tempFile.txt");
        //for at sørge for at vi ikke skriver til en existerende fil fjerner vi filer ved navn "tempFile.txt" før vi forsøger at skabe den
        if (!newFile.exists()) newFile.delete();
        newFile.createNewFile();
        //initialiserer vores writer for at kunne skrive til filen
        PrintWriter pw = new PrintWriter(newFile);
        boolean exists = false;
        //tjekker igennem alle medier allerede på favorites listen og hvis mediet er der i forvejen noterer vi at det var der ellers tilføjes det til den nye fil
        try{
            for (String s:load()){
                if(data.equals(s)) {
                    //her sættes exists til true og den skrives ikke til den nye fil.
                    exists = true;
                }
                else {
                    pw.println(s);
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException();
        }
        finally {
            //vi lukker vores pw inden vi kaster en exception
            pw.close();
        }

        //hvis ikke vi har set mediet kaster vi en exception
        if(!exists){
            throw new FavoriteAddRemoveException("does not exist", data);
        }
        //instancierer den gamle fil
        File favorites = new File(filePath);
        //erstatter den gamle fil med den nye
        favorites.delete();
        newFile.renameTo(favorites);
    }
    public void addFavorite(String data) throws FavoriteAddRemoveException, IOException{
        //her laver vi den fil som bliver til "favorit.txt" filen
        File favorites = new File(filePath);
        //hvis der ikke existerer en favorites fil laver vi den
        if (!favorites.exists()) favorites.createNewFile();
        //initialiserer vores writer for at kunne skrive til filen
        PrintWriter pw = new PrintWriter(favorites);
        //tjekker igennem alle medier allerede på favorites listen og hvis mediet er der i forvejen kaster vi en exception
        for (String s:load()){
            if(data.equals(s)){
                throw new FavoriteAddRemoveException("exists", data);
            }
        }
        //hvis ikke vi har set mediet endnu skrives det til filen
        pw.println(data);
        pw.close();
    }
}
