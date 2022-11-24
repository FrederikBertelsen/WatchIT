package presentationLayer;

import javax.swing.*;
import java.awt.*;

public class GalleryPanel extends JPanel {
//    public GalleryPanel(String name, double rating, String[] genres, int year) {
    public GalleryPanel(GridLayout layout) {
        super(layout);

//        setBounds(40, 80, 50, 100);
        setBackground(Color.lightGray);

        // temp border
        setBorder(BorderFactory.createBevelBorder(0));


        JLabel name = new JLabel("The Batman Love Affair 2: Bat to the face!");
        add(name);
        JLabel rating = new JLabel("Rating: 9.8");
        add(rating);
        JLabel genres = new JLabel("Horror, Comedy, Romance");
        add(genres);
        JButton playButton = new JButton("Play Movie");
        add(playButton);
    }
}
