package domainLayer;

import java.util.ArrayList;

public class Show extends Media{
    private int fromYear;
    private int toYear;

    private ArrayList<Season> seasons;

    public int getSeasonCount(){
        return seasons.size();
    }
}
