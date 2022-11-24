package dataLayer;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface DataHandler {

    BufferedImage getImage(String mediaName)throws IOException;

    ArrayList<String> load()throws IOException;

    void saveFavourite(String data);
}
