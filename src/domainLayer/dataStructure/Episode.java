package domainLayer.dataStructure;

public class Episode implements Playable{

    private int seasonNumber;
    private int episodeNumber;
    public Episode(int seasonNumber, int episodeNumber){
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }
    public int getEpisodeNumber(){
        return episodeNumber;
    }
    @Override
    public void play() {
        System.out.println("PLAYING: Season " + seasonNumber + " Episode " + episodeNumber);
    }
}
