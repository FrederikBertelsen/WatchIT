package domainLayer.dataStructure;

import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private ArrayList<Episode> episodes;

    public Season(int seasonNumber, ArrayList<Episode> episodes){
        this.seasonNumber = seasonNumber;
        this.episodes = episodes;
    }
    public int getEpisodeCount(){
        return episodes.size();
    }
}
