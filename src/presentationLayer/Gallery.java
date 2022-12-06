package presentationLayer;

import domainLayer.dataStructure.Movie;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {
    public GalleryPanel[] currentGalleryPanels;
    public Gallery(GridLayout layout) {
        super(layout);

//        setBounds(40, 80, 50, 100);
        setBackground(Color.gray);

        currentGalleryPanels = new GalleryPanel[0];
    }

    public void updatePanels(Movie[] movies){
        removeCurrentPanels(movies.length);

        for (int i = 0; i < movies.length; i++) {
            GalleryPanel newGalleryPanel = new GalleryPanel(movies[i]);
            currentGalleryPanels[i] = newGalleryPanel;
            add(newGalleryPanel);
        }
    }
    private void removeCurrentPanels(int newLength){
        for(GalleryPanel galleryPanel : currentGalleryPanels){
            remove(galleryPanel);
        }
        currentGalleryPanels = new GalleryPanel[newLength];
    }
}
