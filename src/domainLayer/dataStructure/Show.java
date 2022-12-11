package domainLayer.dataStructure;

import java.awt.image.BufferedImage;

public class Show extends Media {
    private boolean hasEnded;
    private int toYear;
    private Season[] seasons;

    public Show(String title, int releaseYear, int toYear, String[] genres, double rating, Season[] seasons, BufferedImage image, boolean favorited) {
        super(title, releaseYear, genres, rating, image, favorited);
        if (toYear > 0){
            hasEnded = true;
            this.toYear = toYear;
        }
        else {
            hasEnded = false;
        }
        this.seasons = seasons;
    }


    public int getSeasonCount() {
        return seasons.length;
    }

    @Override
    public String getYearString() {
        if (hasEnded){
            return super.getYearString() + " - " + toYear;
        }
        return super.getYearString() + " - NU   ";
    }
}
