package domainLayer;

import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public interface DataBase {
    Movie[] getMovies();

    Show[] getShows();

    Movie[] movieSerializer(ArrayList<String> moviesStrings) throws IOException;
    Movie[] movieSerializer(ArrayList<String> moviesStrings, HashSet<String> favorites) throws IOException;

    Show[] showSerializer(ArrayList<String> showStrings) throws IOException;
    Show[] showSerializer(ArrayList<String> showStrings, HashSet<String> favorites) throws IOException;

    ArrayList<Media> getFilteredMedia(SearchPreset searchPreset);

    ArrayList<Media> getMediaByType(HashSet<String> types);
    ArrayList<Media> filterByGenre(ArrayList<Media> mediaList, HashSet<String> genres);
    ArrayList<Media> filterByRating(ArrayList<Media> mediaList, double rating);
    ArrayList<Media> filterByYear(ArrayList<Media> mediaList, int yearStart, int yearEnd);

    ArrayList<Media> filterByFavoritesOnly(ArrayList<Media> mediaList);

    void addToFavorites(Media media);
    void removeFromFavorites(Media media);
}
