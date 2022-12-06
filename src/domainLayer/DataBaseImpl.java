package domainLayer;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import domainLayer.dataStructure.*;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseImpl implements DataBase {
    private Movie[] movies;
    private Show[] shows;

    private DataHandler movieDataHandler;
    private String moviePath = "data/film.txt";
    private String movieImageFolderPath = "data/filmplakater";

    private DataHandler showDataHandler;
    private String showPath = "data/serier.txt";
    private String showImageFolderPath = "data/serieforsider";

    public DataBaseImpl() {
        movieDataHandler = new DataHandlerImpl(moviePath, movieImageFolderPath);
        showDataHandler = new DataHandlerImpl(showPath, showImageFolderPath);

        try {
            movieLoader(movieDataHandler.load());
            showLoader(showDataHandler.load());
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading movie and show files: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error in loading movie and show images: " + e.getMessage());
        }

    }

    public Movie[] getMovies() {
        return movies;
    }

    public Show[] getShows() {
        return shows;
    }

    @Override
    public void movieLoader(ArrayList<String> moviesStrings) throws IOException {
        movies = new Movie[moviesStrings.size()];
        // for hver linje af data, lav et Movie object der indeholder data'en
        for (int i = 0; i < moviesStrings.size(); i++) {
            String[] stringParts = moviesStrings.get(i).split("; ?");

            String title = stringParts[0];

            int year = Integer.parseInt(stringParts[1]);

            String[] genres = stringParts[2].split(", ");

            stringParts[3] = stringParts[3].replace(",", ".");
            double rating = Double.parseDouble(stringParts[3]);

            BufferedImage image = movieDataHandler.getImage(title);
            if (image == null) {
                System.out.println("Image for '" + title + "' not found!");
            }

            Movie movie = new Movie(title, year, genres, rating, image);
            movies[i] = movie;
        }
    }

    @Override
    public void showLoader(ArrayList<String> showStrings) throws IOException {
        shows = new Show[showStrings.size()];

        for (int i = 0; i < showStrings.size(); i++) {
            String[] stringParts = showStrings.get(i).split("; ?");

            String title = stringParts[0];


            String[] years = stringParts[1].split(" ?- ?");
            int releaseYear = Integer.parseInt(years[0].strip());
            int toYear = 0;
            if (years.length > 1) toYear = Integer.parseInt(years[1].strip());

            String[] genres = stringParts[2].split(", ?");

            stringParts[3] = stringParts[3].replace(",", ".");
            double rating = Double.parseDouble(stringParts[3]);

            String[] seasonStrings = stringParts[4].split(", ?");
            Season[] seasons = new Season[seasonStrings.length];
            for (int j = 0; j < seasonStrings.length; j++) {
                String[] seasonStringParts = seasonStrings[j].split("-");

                int seasonNumber = Integer.parseInt(seasonStringParts[0].strip());
                int episodeCount = Integer.parseInt(seasonStringParts[1].strip());

                Episode[] episodes = new Episode[episodeCount];
                for (int k = 0; k < episodeCount; k++) {
                    Episode newEpisode = new Episode(i);
                    episodes[k] = newEpisode;
                }
                Season newSeason = new Season(seasonNumber, episodes);
                seasons[j] = newSeason;
            }


            BufferedImage image = showDataHandler.getImage(title);
            if (image == null) {
                System.out.println("Image for '" + title + "' not found!");
            }

            Show show = new Show(title, releaseYear, toYear, genres, rating, seasons, image);
            shows[i] = show;
        }

    }

    // these are temporarily set to public to test
    @Override
    public ArrayList<Media> filterByGenre(ArrayList<Media> inputList, String[] genres) {
        ArrayList<Media> output = new ArrayList<>();

        for (Media media : inputList) {
            boolean match = true;
            for (String genre : genres) {
                if (!media.getGenres().contains(genre)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                output.add(media);
            }
        }

        return output;
    }

    @Override
    public ArrayList<Media> filterByRating(ArrayList<Media> inputList, double rating) {
        ArrayList<Media> output = new ArrayList<>();

        for (Media media : inputList) {
            if (media.getRating() >= rating) {
                output.add(media);
            }
        }

        return output;
    }

    @Override
    public ArrayList<Media> filterByYear(ArrayList<Media> inputList, double yearStart, double yearEnd) {
        ArrayList<Media> output = new ArrayList<>();

        for (Media media : inputList) {
            if (yearStart <= media.getRating() && media.getRating() <= yearEnd) {
                output.add(media);
            }
        }

        return output;
    }

}
