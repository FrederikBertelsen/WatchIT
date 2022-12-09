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

//        ArrayList<Media> test = new ArrayList<>();
//        Collections.addAll(test,db.getShows());
//        Collections.addAll(test,db.getMovies());
//
//        int lowestYear = Integer.MAX_VALUE;
//        int highestYear = Integer.MIN_VALUE;
//        for (Media media : test){
//            int releaseYear = media.getReleaseYear();
//
//            if (releaseYear > highestYear){
//                highestYear = releaseYear;
//            }
//            if (releaseYear < lowestYear){
//                lowestYear = releaseYear;
//            }
//        }
//        System.out.println(lowestYear);
//        System.out.println(highestYear);

    }

    public static void updateUI() {
        ArrayList<Media> medias = db.filterMedia(ui.getSelectedTypes(), ui.getSelectedGenres(), ui.getSelectedRating(), ui.getSelectedYear(), ui.getSelectedSortBy(), ui.getSelectedSortByDirection(), ui.getSearchTerm());
        ui.updateGallery(medias);
    }

    public static void goToDetailsView(Media media) {
        ui.goToDetailsView(media);
    }
}
