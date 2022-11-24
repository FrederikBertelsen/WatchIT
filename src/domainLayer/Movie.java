package domainLayer;

import java.util.ArrayList;

public class Movie extends Media implements Playable{
    private int year;

    public Movie(String title, int year, ArrayList<String> genres, double rating){
        super(title, genres, rating);
        this.year = year;

    }

    public int getYear() {
        return year;
    }

    @Override
    public void play() {

    }
}
