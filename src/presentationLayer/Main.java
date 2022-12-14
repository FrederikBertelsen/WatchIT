package presentationLayer;

import domainLayer.DataBase;
import domainLayer.DataBaseImpl;
import domainLayer.SearchPreset;
import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Playable;

import java.util.ArrayList;

public abstract class Main {
    private static WatchItUI ui;
    private static DataBase db;

    private static VideoPlayer videoPlayer;

    public static void main(String[] args) {
        // Opret en instans af WatchItUI-klassen med navnet "WatchIT".
        ui = new WatchItUI("WatchIT");

        // Opret en instans af DataBaseImpl-klassen.
        db = new DataBaseImpl();

        // Opdater galleriet i brugergrænsefladen så alle film og serier bliver vist når programmet starter
        ui.updateGallery();
    }

    // Denne metode skifter til galleri-visnings-tilstanden i brugergrænsefladen.
    public static void goToGalleryView() {
        // Brug WatchItUI-objektet til at skifte til galleri-visnings-tilstanden.
        ui.goToGalleryView();
    }

    // Denne metode skifter til detalje-visnings-tilstanden for et bestemt medie i brugergrænsefladen.
    public static void goToDetailsView(Media media) {
        // Brug WatchItUI-objektet til at skifte til detalje-visnings-tilstanden for det angivne medie.
        ui.goToDetailsView(media);
    }

    // Denne metode afspiller et bestemt medie.
    public static void play(Media media, Playable playable) {
        if (videoPlayer != null) videoPlayer.dispose();

        videoPlayer = new VideoPlayer(media, playable);
    }

    // Denne Metoden returnerer en liste af medier, der opfylder filtreringskriterierne.
    // Filtreringskriterierne inkluderer typer, genrer, rating, år, sortering og søgeord.
    public static ArrayList<Media> filterMedia(SearchPreset searchPreset) {
        return db.getFilteredMedia(searchPreset);
    }

    public static void addFavorite(Media media) {
        db.addToFavorites(media);
    }

    public static void removeFavorite(Media media) {
        db.removeFromFavorites(media);
    }
}
