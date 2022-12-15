package domainLayer.dataStructure;

public class Episode implements Playable{

    private int seasonNumber;
    private int episodeNumber;
    public Episode(int seasonNumber, int episodeNumber){
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }
    @Override
    public String getPath() {
        return "Season " + seasonNumber + "/Episode " + episodeNumber + ".mp4";
    }
}
