package domainLayer;

import domainLayer.dataStructure.Media;
import presentationLayer.WatchItUI;

import java.util.ArrayList;

public class Main {
    public static WatchItUI ui;
    public static DataBase db;

    public static void main(String[] args) {
        ui = new WatchItUI("WatchIT");
        db = new DataBaseImpl();

        updateUI();
    }

    public static void updateUI() {
        ArrayList<Media> medias = db.filterMedia(ui.getSelectedTypes(), ui.getSelectedGenres(), ui.getSelectedRating(), ui.getSelectedYear(), ui.getSelectedSortBy(), ui.getSelectedSortByDirection(), ui.getSearchTerm());
        ui.updateGallery(medias);
    }
}
