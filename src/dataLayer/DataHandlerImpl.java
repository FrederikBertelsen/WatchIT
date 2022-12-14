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

    private boolean writePermission;

    public DataHandlerImpl(String filePath) {
        this.filePath = filePath;
        this.imageFolderPath = null;
    }

    public DataHandlerImpl(String filePath, boolean writePermission) {
        this(filePath);
        this.writePermission = writePermission;
    }

    public DataHandlerImpl(String filePath, String imageFolderPath) {
        this.filePath = filePath;
        this.imageFolderPath = imageFolderPath;
    }

    public DataHandlerImpl(String filePath, String imageFolderPath, boolean writePermission) {
        this(filePath, imageFolderPath);
        this.writePermission = writePermission;

    }

    public BufferedImage getImage(String mediaName) throws IOException {
        String path = imageFolderPath + "/" + mediaName + ".jpg";
        BufferedImage result = ImageIO.read(new File(path));
        return result;
    }

    public ArrayList<String> loadData() throws FileNotFoundException {
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
    public void addFromFile(String newLine) throws FavoriteAddRemoveException, IOException {
        if (!writePermission) throw new IOException("Fil overskrivning ikke tillad af DataHandler.");


        //her laver vi den fil som bliver til "favorit.txt" filen
        File dataFile = new File(filePath);
        //hvis der ikke existerer en favorites fil laver vi den
        if (!dataFile.exists()) dataFile.createNewFile();

        //tjekker igennem alle medier allerede på favorites listen og hvis mediet er der i forvejen kaster vi en exception
        ArrayList<String> lines = new ArrayList<>();

        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            String line = s.nextLine();

            if (newLine.equals(line)) {
                throw new FavoriteAddRemoveException("Dataen er allerede i filen:\n", newLine);
            }
            lines.add(line);
        }


        //initialiserer vores writer for at kunne skrive til filen
        PrintWriter pw = new PrintWriter(dataFile);

        for (String line : lines) {
            pw.println(line);
        }
        pw.println(newLine);

        pw.close();
    }

    public void removeFromFile(String newLine) throws FavoriteAddRemoveException, IOException {
        if (!writePermission) throw new IOException("Fil overskrivning ikke tillad af DataHandler.");

        //her laver vi den fil som bliver til "favorit.txt" filen
        File dataFile = new File(filePath);
        //hvis der ikke existerer en favorites fil laver vi den
        if (!dataFile.exists()) throw new IOException("Fil ikke fundet: " + filePath);

        //tjekker igennem alle medier allerede på favorites listen og hvis mediet er der i forvejen kaster vi en exception
        ArrayList<String> lines = new ArrayList<>();
        boolean lineFound = false;

        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            String line = s.nextLine();

            if (!newLine.equals(line)) {
                lines.add(line);
                lineFound = true;
            }
        }

        if (!lineFound) {
            throw new FavoriteAddRemoveException("Dataen ikke fundet i filen:\n", newLine);
        }

        //initialiserer vores writer for at kunne skrive til filen
        PrintWriter pw = new PrintWriter(dataFile);

        for (String line : lines) {
            pw.println(line);
        }

        pw.close();
    }
}
