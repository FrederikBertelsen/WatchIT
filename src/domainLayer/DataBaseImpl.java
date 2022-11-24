package domainLayer;

import dataLayer.DataHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseImpl implements DataBase{

    public ArrayList<Movie> movieSplitter(String[] movies){
        ArrayList<Movie> movieList = new ArrayList<>();
        for (String string : movies){

            String[] parts = string.split("; ?");

            String title = parts[0];
            int year = Integer.parseInt(parts[1]);

            parts[3].replace(",", ".");
            double rating = Double.parseDouble(parts[3]);


            ArrayList<String> genres = new ArrayList<>(Arrays.asList(parts[2].split(", ?"))) ;

            Movie movie = new Movie(title, year, genres, rating);
            movieList.add(movie);
        }
        return movieList;
    }

    public ArrayList<Show> showSplitter(String[] shows){
        ArrayList<Show> showList = new ArrayList<>();
        for (String string : shows){

            String[] parts = string.split("; ?");
            String title = parts[0];

            String[] years = parts[1].split("-");
            int fromYear = Integer.parseInt(years[0]);
            int toYear = Integer.parseInt(years[1]);


            parts[3].replace(",", ".");
            double rating = Double.parseDouble(parts[3]);

            ArrayList<String> genres = new ArrayList<>(Arrays.asList(parts[2].split(", ?"))) ;

            Show show = new Show(title, fromYear, toYear, genres, rating);
            showList.add(show);
        }
        return showList;
    }


}
