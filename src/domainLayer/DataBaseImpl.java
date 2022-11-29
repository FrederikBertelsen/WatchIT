package domainLayer;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import domainLayer.dataStructure.Episode;
import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Season;
import domainLayer.dataStructure.Show;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseImpl implements DataBase {
    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;

    private DataHandler movieDataHandler;
    private String moviePath = "data/film.txt";
    private String movieImageFolderPath = "data/filmplakater";

    private DataHandler showDataHandler;
    private String showPath = "data/serier.txt";
    private String showImageFolderPath = "data/serieforsider";

    public DataBaseImpl(){
        movieDataHandler = new DataHandlerImpl(moviePath, movieImageFolderPath);
        showDataHandler = new DataHandlerImpl(showPath, showImageFolderPath);

        try {
            movieLoader(movieDataHandler.load());
            showLoader(showDataHandler.load());
        }catch (FileNotFoundException e){
            System.out.println("Error in loading movie and show files: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error in loading movie and show images: " + e.getMessage());
        }

    }

    @Override
    public void movieLoader(ArrayList<String> moviesStrings) throws IOException {
        movies = new ArrayList<>();

        for (String string : moviesStrings) {

            String[] parts = string.split("; ?");

            String title = parts[0];

            int year = Integer.parseInt(parts[1]);

            ArrayList<String> genres = new ArrayList<>(Arrays.asList(parts[2].split(", ")));

            parts[3] = parts[3].replace(",", ".");
            double rating = Double.parseDouble(parts[3]);

            BufferedImage image = movieDataHandler.getImage(title);
            if (image == null){
                System.out.println(title);
            }

            Movie movie = new Movie(title, year, genres, rating, image);
            movies.add(movie);
        }
    }

    @Override
    public void showLoader(ArrayList<String> showStrings) throws IOException {
        shows = new ArrayList<>();

        for (String string : showStrings) {

            String[] parts = string.split("; ?");
            String title = parts[0];

            String[] years = parts[1].split("-");
            int releaseYear = Integer.parseInt(years[0]);
            int toYear = Integer.parseInt(years[1]);

            ArrayList<String> genres = new ArrayList<>(Arrays.asList(parts[2].split(", ?")));

            parts[3] = parts[3].replace(",", ".");
            double rating = Double.parseDouble(parts[3]);

            ArrayList<Season> seasons = new ArrayList<>();
            for (String seasonString : parts[4].split(", ")) {
                String[] str = seasonString.split("-");
                int seasonNumber = Integer.parseInt(str[0]);
                int episodeCount = Integer.parseInt(str[1]);

                ArrayList<Episode> episodes = new ArrayList<>();
                for (int i = 0; i < episodeCount + 1; i++) {
                    Episode newEpisode = new Episode(i);
                    episodes.add(newEpisode);
                }
                Season newSeason = new Season(seasonNumber, episodes);
                seasons.add(newSeason);
            }

            BufferedImage image = showDataHandler.getImage(title);

            Show show = new Show(title, releaseYear, toYear, genres, rating, seasons, image);
            shows.add(show);
        }

    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public ArrayList<Show> getShows(){
        return shows;
    }

}
