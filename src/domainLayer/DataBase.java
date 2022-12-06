package domainLayer;

import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Show;

import java.io.IOException;
import java.util.ArrayList;

public interface DataBase {
    Movie[] getMovies();

    Show[] getShows();

    void movieLoader(ArrayList<String> movieStrings) throws IOException;

    void showLoader(ArrayList<String> showStrings) throws IOException;

    ArrayList<Media> filterByGenre(ArrayList<Media> inputList, String[] genres);

    ArrayList<Media> filterByRating(ArrayList<Media> inputList, double rating);

    ArrayList<Media> filterByYear(ArrayList<Media> inputList, double yearStart, double yearEnd);
}
