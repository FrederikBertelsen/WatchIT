package testFolder;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import domainLayer.DataBase;
import domainLayer.DataBaseImpl;
import domainLayer.SearchPreset;
import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class domainLayerTest {
    DataBase dataBase;

    @BeforeEach
    public void setUp(){
        dataBase = new DataBaseImpl();
    }

    @Test
    public void getTitleTest() throws IOException {
        DataHandler movieDataHandler = new DataHandlerImpl("data/film.txt");
        ArrayList<String> load = movieDataHandler.loadData();

        dataBase.movieSerializer(load);

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
        Movie[] movies = dataBase.getMovies();

        String[] parts = moviesInputStrings.get(0).split("; ?");
        Movie currentMovie = movies[0];

        assertEquals(parts[0], currentMovie.getTitle());
        assertEquals(Integer.parseInt(parts[1]), currentMovie.getReleaseYear());
        assertEquals(new HashSet<>(Arrays.asList(parts[2].split(", "))), currentMovie.getGenres());
        assertEquals(Double.parseDouble(parts[3].replace(",", ".")), currentMovie.getRating());

        parts = moviesInputStrings.get(1).split("; ?");
        currentMovie = movies[15];

        assertEquals(parts[0], currentMovie.getTitle());
        assertEquals(Integer.parseInt(parts[1]), currentMovie.getReleaseYear());
        assertEquals(new HashSet<>(Arrays.asList(parts[2].split(", "))), currentMovie.getGenres());
        assertEquals(Double.parseDouble(parts[3].replace(",", ".")), currentMovie.getRating());

    }

    @Test
    public void movieFilteringTest() {
        HashSet<String> types = new HashSet<>();
        types.add("Film");

        HashSet<String> genres = new HashSet<>();
        genres.add("Action");
        genres.add("Drama");

        String rating = "> 5.0";
        String years = "1940-1998";
        String sortBy = "Rating";
        String sortByDirection = "Faldende";
        String searchTerm = "";
        boolean favoritesOnly = false;

        SearchPreset searchPreset = new SearchPreset(types, genres, rating, years, sortBy, sortByDirection, searchTerm, favoritesOnly);
        ArrayList<Media> filteredMedia = dataBase.getFilteredMedia(searchPreset);

        boolean error = false;
        for (Media media : filteredMedia){
            if (!(media instanceof Movie)) error = true;
            if (!media.getGenres().contains("Action") || !media.getGenres().contains("Drama")) error = true;
            if (media.getRating() < 5.0) error = true;
            if (media.getReleaseYear() < 1940 || media.getReleaseYear() > 1998) error = true;
        }
        assertFalse(error);
    }
}


