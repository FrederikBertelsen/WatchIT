package domainLayer;

import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Show;

import java.io.IOException;
import java.util.ArrayList;

public interface DataBase {
    public void movieSplitter(ArrayList<String> movieStrings) throws IOException;
    public void showSplitter(ArrayList<String> showStrings) throws IOException;
}
