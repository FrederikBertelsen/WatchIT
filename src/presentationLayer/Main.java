package presentationLayer;

import domainLayer.DataBase;
import domainLayer.DataBaseImpl;
import domainLayer.dataStructure.Media;

public class Main {
    public static WatchItUI ui;
    public static DataBase db;

    public static void main(String[] args) {
        ui = new WatchItUI("WatchIT");
        db = new DataBaseImpl();

        ui.updateGallery();

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

    public static void goToGalleryView() {
        ui.goToGalleryView();
    }
    public static void goToDetailsView(Media media) {
        ui.goToDetailsView(media);
    }

    public static void play(Media media){
        System.out.println("Playing: " + media.getTitle());
    }
}
