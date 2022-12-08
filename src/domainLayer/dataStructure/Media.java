package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class Media {
    private BufferedImage image;
    private String title;
    private HashSet<String> genres;
    private double rating;

    private int releaseYear;


    public Media(String title, int releaseYear, String[] genres, double rating, BufferedImage image) {
        this.title = title;
        this.genres = new HashSet<>(List.of(genres));
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public HashSet<String> getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public BufferedImage getImage() {
        return image;
    }
}
