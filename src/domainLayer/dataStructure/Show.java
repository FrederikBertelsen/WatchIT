package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Show extends Media{
    private int toYear;
    private Season[] seasons;

    public Show(String title, int releaseYear, int toYear, String[] genres, double rating, Season[] seasons, BufferedImage image){
        super(title, releaseYear, genres, rating, image);
        this.toYear = toYear;
        this.seasons = seasons;
    }


    public int getSeasonCount(){
        return seasons.length;
    }
}
