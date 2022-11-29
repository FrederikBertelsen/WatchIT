package presentationLayer;

import domainLayer.dataStructure.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gallery extends JPanel {
    public ArrayList<GalleryPanel> currentGalleryPanels;
    public Gallery(GridLayout layout) {
        super(layout);

//        setBounds(40, 80, 50, 100);
        setBackground(Color.gray);

        currentGalleryPanels = new ArrayList<>();
    }

    public void updatePanels(ArrayList<Movie> movies){
        removeCurrentPanels();

        for (Movie movie : movies){
            GalleryPanel newGalleryPanel = new GalleryPanel(movie);
            add(newGalleryPanel);
        }
    }
    public void removeCurrentPanels(){
        for (GalleryPanel galleryPanel : currentGalleryPanels){
            remove(galleryPanel);
        }
        currentGalleryPanels = new ArrayList<>();
    }
}
