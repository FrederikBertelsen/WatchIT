package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;

public abstract class Media {
    private BufferedImage image;
    private String title;
    private HashSet<String> genres;
    private double rating;
    private int releaseYear;
    private boolean favorited;

    public Media(String title, int releaseYear, String[] genres, double rating, BufferedImage image, boolean favorited) {
        this.title = title;
        this.genres = new HashSet<>(List.of(genres));
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.image = image;
        this.favorited = favorited;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }
    public String getRatingString() {
        return String.valueOf(rating).replace(".",",");
    }

    public HashSet<String> getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public String getYearString() {
        return String.valueOf(releaseYear);
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isFavorited(){
        return favorited;
    }
    public void setFavorite(boolean value){
        favorited = value;
    }
}
