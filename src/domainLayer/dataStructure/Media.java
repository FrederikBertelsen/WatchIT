package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Media {
    private BufferedImage image;
    private String title;
    private String[] genres;
    private double rating;

    private int releaseYear;


    public Media(String title, int releaseYear, String[] genres, double rating, BufferedImage image){
        this.title = title;
        this.genres = genres;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.image = image;
    }

    public String getTitle(){
        return title;
    }

    public double getRating(){
        return rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public BufferedImage getImage() {
        return image;
    }
}
