package presentationLayer;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {
    //    public GalleryPanel(String name, double rating, String[] genres, int year) {
    public Gallery(GridLayout layout) {
        super(layout);

//        setBounds(40, 80, 50, 100);
        setBackground(Color.gray);

        for (int i = 0; i < 99; i++) {
            GalleryPanel galleryPanel = new GalleryPanel(new GridLayout(0,1));
            add(galleryPanel);
        }
    }
}
