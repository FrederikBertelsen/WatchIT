package testFolder;
import java.io.IOException;
import java.util.*;

import dataLayer.DataHandlerImpl;
import domainLayer.DataBase;
import domainLayer.DataBaseImpl;
import domainLayer.dataStructure.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class domainLayerTest {
    private DataBase dataBase;

    @BeforeEach
    public void setUp(){
        dataBase = new DataBaseImpl();
    }

    @Test
    public void getTitleTest() throws IOException {
        DataHandlerImpl movieDataHandler = new DataHandlerImpl("data/film.txt");
        ArrayList<String> load = movieDataHandler.load();

        dataBase.movieLoader(load);

        Movie[] movies = dataBase.getMovies();
        int i = 1;
        for (Movie movie : movies){
            System.out.println(i + " " + movie.getTitle() );
            i++;
        }
    }
    @Test
    public void movieLoaderTest() throws IOException {
        ArrayList<String> moviesInputStrings = new ArrayList<>();
        moviesInputStrings.add("The Godfather; 1972; Crime, Drama; 9,2;");
        moviesInputStrings.add("Forrest Gump; 1994; Drama, Romance; 8,8;");

        //dataBase = new DataBaseImpl();
        dataBase.movieLoader(moviesInputStrings);
        Movie[] movies = dataBase.getMovies();

        for (int i = 0; i < movies.length; i++) {
            String[] parts = moviesInputStrings.get(i).split("; ?");
            Movie currentMovie = movies[i];

            assertEquals(parts[0], currentMovie.getTitle());
            assertEquals(Integer.parseInt(parts[1]), currentMovie.getYear());
            assertEquals(new ArrayList<>(Arrays.asList(parts[2].split(", "))), currentMovie.getGenres());
            assertEquals(Double.parseDouble(parts[3].replace(",", ".")), currentMovie.getRating());
        }
    }
}


