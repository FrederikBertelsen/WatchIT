package dataLayer;

public class FavoriteAddRemoveException extends Exception{
    private String mediaTitle;
    FavoriteAddRemoveException(String exceptionMessage, String mediaTitle){
        super(exceptionMessage + "\n" + mediaTitle);
        this.mediaTitle = mediaTitle;
    }


    String getMediatitle(){
        return mediaTitle;
    }
}
