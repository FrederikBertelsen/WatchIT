package domainLayer;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import domainLayer.dataStructure.*;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

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

            //TODO: add favorited functionality
            Movie movie = new Movie(title, year, genres, rating, image, false);
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

            // TODO: create favorited functionality
            Show show = new Show(title, releaseYear, toYear, genres, rating, seasons, image, false);
            shows[i] = show;
        }

    }

    @Override
    public ArrayList<Media> filterMedia(HashSet<String> types, HashSet<String> genres, String rating, String years, String sortBy, String sortByDirection, String searchTerm) {
        // filter by types
        ArrayList<Media> output = getByType(types);
        if (output.size() == 0) return output;

        // filter by genres
        output = filterByGenre(output, genres);

        // filter by rating
        if (!rating.equals("")) {
            double ratingDouble = Double.parseDouble(rating.substring(1));
            output = filterByRating(output, ratingDouble);
        }

        //filter by year range
        if (!years.equals("")) {
            String[] yearParts = years.split("-");
            int yearFrom = Integer.parseInt(yearParts[0].strip());
            int yearTo = Integer.parseInt(yearParts[1].strip());

            output = filterByYear(output, yearFrom, yearTo);
        }


        // sort by
        if (!sortBy.equals("")) {
            if (sortBy.equals("Titel")) {
                output.sort(Comparator.comparing(Media::getTitle));
            } else if (sortBy.equals("Rating")) {
                output.sort(Comparator.comparing(Media::getRating));
            } else if (sortBy.equals("Årstal")) {
                output.sort(Comparator.comparing(Media::getYear));
            }
        }

        if (!searchTerm.equals("")) {
            output = StringMatcher.getMatches(searchTerm, output);
        }

        // sort by direction
        if (sortByDirection.equals("Faldende")) {
            Collections.reverse(output);
        }

        return output;
    }

    @Override
    public ArrayList<Media> getByType(HashSet<String> types) {
        ArrayList<Media> output = new ArrayList<>();

        if (types.size() == 0) {
            Collections.addAll(output, getMovies());
            Collections.addAll(output, getShows());
        } else if (types.contains("Film")) {
            Collections.addAll(output, getMovies());
        } else if (types.contains("Serier")) {
            Collections.addAll(output, getShows());
        }

        return output;
    }

    @Override
    public ArrayList<Media> filterByGenre(ArrayList<Media> inputList, HashSet<String> genres) {
        if (genres.size() == 0) return inputList;

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
    public ArrayList<Media> filterByYear(ArrayList<Media> inputList, int yearStart, int yearEnd) {
        ArrayList<Media> output = new ArrayList<>();

        for (Media media : inputList) {
            if (yearStart <= media.getYear() && media.getYear() <= yearEnd) {
                output.add(media);
            }
        }

        return output;
    }

}
