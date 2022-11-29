package dataLayer;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataHandler {

    BufferedImage getImage(String mediaName)throws IOException;

    ArrayList<String> load()throws FileNotFoundException;

    void saveFavourite(String data) throws IOException;
}
