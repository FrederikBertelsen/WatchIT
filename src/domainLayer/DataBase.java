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

    void movieLoader(ArrayList<String> movieStrings) throws IOException;

    void showLoader(ArrayList<String> showStrings) throws IOException;

    ArrayList<Media> filterMedia(HashSet<String> types, HashSet<String> genres, String rating, String years, String sortBy, String sortByDirection, String SearchTerm);

    ArrayList<Media> getByType(HashSet<String> types);
    ArrayList<Media> filterByGenre(ArrayList<Media> inputList, HashSet<String> genres);
    ArrayList<Media> filterByRating(ArrayList<Media> inputList, double rating);
    ArrayList<Media> filterByYear(ArrayList<Media> inputList, int yearStart, int yearEnd);
}
