package domainLayer;

import java.util.ArrayList;

public interface DataBase {
    public ArrayList<Movie> movieSplitter(String[] movies);
    public ArrayList<Show> showSplitter(String[] shows);
}
