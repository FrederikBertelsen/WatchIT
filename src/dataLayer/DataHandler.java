package dataLayer;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataHandler {

    BufferedImage getImage(String mediaName)throws IOException;

    ArrayList<String> load()throws FileNotFoundException;

    /*kaster en exception hvis den ikke kan oprette en tempfil til favorites/ normal fil til favorites.
    Dette er nødvendigt for at den kan opdatere txt filen eller oprette den hvis den ikke existerer endnu.
    Dette burde ikke ske medmindre brugeren har den lokation låst på en måde hvor den ikke kan slettes da funktionen også forsøger dette
     */
    void addFavorite(String data) throws FavoriteAddRemoveException,IOException;

    void removeFavorite(String data) throws FavoriteAddRemoveException, IOException;
}
