package domainLayer.dataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Show extends Media{
    private int toYear;
    private ArrayList<Season> seasons;

    public Show(String title, int releaseYear, int toYear, ArrayList<String> genres, double rating, ArrayList<Season> seasons, BufferedImage image){
        super(title, releaseYear, genres, rating, image);
        this.toYear = toYear;
        this.seasons = seasons;
    }


    public int getSeasonCount(){
        return seasons.size();
    }
}
