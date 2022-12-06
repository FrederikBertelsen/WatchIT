package domainLayer.dataStructure;

import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private Episode[] episodes;

    public Season(int seasonNumber, Episode[] episodes){
        this.seasonNumber = seasonNumber;
        this.episodes = episodes;
    }
    public int getEpisodeCount(){
        return episodes.length;
    }
}
