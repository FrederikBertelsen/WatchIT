package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Movie extends Media implements Playable {

    public Movie(String title, int releaseYear, String[] genres, double rating, BufferedImage image, boolean favorited) {
        super(title, releaseYear, genres, rating, image, favorited);


    }

    @Override
    public void play() {
        System.out.println("PLAYING: " + getTitle());
    }
}
