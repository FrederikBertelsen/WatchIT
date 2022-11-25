package domainLayer;

import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Show;

import java.util.ArrayList;

public interface DataBase {
    public ArrayList<Movie> movieSplitter(ArrayList<String> movieStrings);
    public ArrayList<Show> showSplitter(ArrayList<String> showStrings);
}
