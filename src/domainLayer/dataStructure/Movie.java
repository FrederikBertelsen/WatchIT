package domainLayer.dataStructure;

import java.util.ArrayList;

public class Movie extends Media implements Playable{

    public Movie(String title, int releaseYear, ArrayList<String> genres, double rating){
        super(title, releaseYear, genres, rating);


    }

    @Override
    public void play() {

    }
}
