package dataLayer;

public class FavoriteAddRemoveException extends Exception{
    private String mediaTitle;
    FavoriteAddRemoveException(String existenceStatement, String mediaTitle){
        super(mediaTitle + " " + existenceStatement);
        this.mediaTitle = mediaTitle;
    }

    String getMediatitle(){
        return mediaTitle;
    }
}
