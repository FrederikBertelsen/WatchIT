package domainLayer;

import domainLayer.dataStructure.Media;

public class MediaScore {
    private Media media;
    private int score;

    public MediaScore(Media media, int score){
        this.media = media;
        this.score = score;
    }

    public Media getMedia() {
        return media;
    }

    public int getScore() {
        return score;
    }
}
