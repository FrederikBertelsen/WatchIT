package domainLayer;

import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Show;

import java.io.IOException;
import java.util.ArrayList;

public interface DataBase {
    void movieLoader(ArrayList<String> movieStrings) throws IOException;
    void showLoader(ArrayList<String> showStrings) throws IOException;

    Movie[] getMovies();

    Show[] getShows();
}
