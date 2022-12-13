package dataLayer;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataHandler {

    BufferedImage getImage(String mediaName)throws IOException;

    ArrayList<String> loadData()throws FileNotFoundException;

    /*kaster en exception hvis den ikke kan oprette en tempfil til favorites/ normal fil til favorites.
    Dette er nødvendigt for at den kan opdatere txt filen eller oprette den hvis den ikke existerer endnu.
    Dette burde ikke ske medmindre brugeren har den lokation låst på en måde hvor den ikke kan slettes da metoden også forsøger dette
     */
    void addFromFile(String data) throws FavoriteAddRemoveException,IOException;

    void removeFromFile(String data) throws FavoriteAddRemoveException, IOException;

    //denne skal kun køres på videoer ekneltvis efter brugeren har bedt den blive afspillet
    //denne skal ikke være void
}
