package domainLayer.dataStructure;

import java.util.ArrayList;

public abstract class Media {
    private String title;
    private ArrayList<String> genres;
    private double rating;

    private int releaseYear;


    public Media(String title, int releaseYear, ArrayList<String> genres, double rating){
        this.title = title;
        this.genres = genres;
        this.rating = rating;
        this.releaseYear = releaseYear;

    }

    public String getTitle(){
        return title;
    }

    public double getRating(){
        return rating;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
