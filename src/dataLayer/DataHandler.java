package dataLayer;


import java.util.List;

public interface DataHandler {

    List<String> load();

    void saveFavourite(String name);
}
