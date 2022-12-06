package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gallery extends JPanel {
    public GalleryPanel[] currentGalleryPanels;
    public Gallery() {
        super(new GridLayout(0, 5));

//        setBounds(40, 80, 50, 100);
        setBackground(Color.gray);

        currentGalleryPanels = new GalleryPanel[0];
    }

    public void updatePanels(ArrayList<Media> movies){
        resetCurrentPanels(movies.size());

        for (int i = 0; i < movies.size(); i++) {
            GalleryPanel newGalleryPanel = new GalleryPanel(movies.get(i));
            currentGalleryPanels[i] = newGalleryPanel;
            add(newGalleryPanel);
        }
    }
    private void resetCurrentPanels(int newLength){
        for(GalleryPanel galleryPanel : currentGalleryPanels){
            remove(galleryPanel);
        }
        currentGalleryPanels = new GalleryPanel[newLength];
    }
}
