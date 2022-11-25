package domainLayer;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import domainLayer.dataStructure.Episode;
import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Season;
import domainLayer.dataStructure.Show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseImpl implements DataBase {
    @Override
    public ArrayList<Movie> movieSplitter(ArrayList<String> moviesStrings) {
        ArrayList<Movie> movieList = new ArrayList<>();
        for (String string : moviesStrings) {

            String[] parts = string.split("; ?");

            String title = parts[0];
            int year = Integer.parseInt(parts[1]);

            parts[3] = parts[3].replace(",", ".");
            double rating = Double.parseDouble(parts[3]);


            ArrayList<String> genres = new ArrayList<>(Arrays.asList(parts[2].split(", ")));

            Movie movie = new Movie(title, year, genres, rating);
            movieList.add(movie);
        }
        return movieList;
    }

    @Override
    public ArrayList<Show> showSplitter(ArrayList<String> shows) {
        ArrayList<Show> showList = new ArrayList<>();
        for (String string : shows) {

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


            Show show = new Show(title, releaseYear, toYear, genres, rating, seasons);
            showList.add(show);
        }
        return showList;
    }


}
