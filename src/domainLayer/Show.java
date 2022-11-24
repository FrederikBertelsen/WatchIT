package domainLayer;

import java.util.ArrayList;

public class Show extends Media{
    private int fromYear;
    private int toYear;

    public Show(String title, int fromYear, int toYear, ArrayList<String> genres, double rating){
        super(title,  genres, rating);
        this.fromYear = fromYear;
        this.toYear = toYear;
    }
    private ArrayList<Season> seasons;

    public int getSeasonCount(){
        return seasons.size();
    }
}
