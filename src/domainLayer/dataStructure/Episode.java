package domainLayer.dataStructure;

public class Episode implements Playable{

    int episodeNumber;
    public Episode(int episodeNumber){
        this.episodeNumber = episodeNumber;
    }
    public int getEpisodeNumber(){
        return episodeNumber;
    }
    @Override
    public void play() {
    }
}
