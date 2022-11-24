package domainLayer;

import java.util.ArrayList;

public abstract class Media {
    private String title;
    private double rating;
    private ArrayList<String> genres;

    public Media(String title, ArrayList<String> genres, double rating){
        this.title = title;
        this.genres = genres;
        this.rating = rating;

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
}
