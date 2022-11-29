package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Movie extends Media implements Playable{

    public Movie(String title, int releaseYear, ArrayList<String> genres, double rating, BufferedImage image){
        super(title, releaseYear, genres, rating, image);


    }

    @Override
    public void play() {

    }
}
