package domainLayer;

import domainLayer.dataStructure.Media;
import presentationLayer.WatchItUI;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        WatchItUI ui = new WatchItUI("WatchIt!");

        DataBase db = new DataBaseImpl();

        ArrayList<Media> medias = new ArrayList<>();
        Collections.addAll(medias, db.getShows());

        // filter test
        medias = db.filterByRating(medias,8);
        medias = db.filterByGenre(medias,new String[]{"Action", "Drama"});

        ui.updateGallery(medias);
    }

}
