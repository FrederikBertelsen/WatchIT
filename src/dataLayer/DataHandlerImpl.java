package dataLayer;

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
        BufferedImage result = ImageIO.read(new File(imageFolderPath + mediaName + ".jpg"));
        return result;
    }

    public ArrayList<String> load() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        //imports the file to read from and creates a Scanner that reads from this file
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            result.add(s.nextLine());
        }
        return result;
    }

    public void saveFavourite(String data) {

    }
}
