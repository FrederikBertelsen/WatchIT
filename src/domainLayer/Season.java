package domainLayer;

import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private ArrayList<Episode> episodes;

    public int getEpisodeCount(){
        return episodes.size();
    }
}
