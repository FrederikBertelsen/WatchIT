package dataLayer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


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

    public void saveFavourite(String data) {

    }
}
