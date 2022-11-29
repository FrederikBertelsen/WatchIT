package presentationLayer;

import domainLayer.dataStructure.Movie;

import javax.swing.*;
import java.awt.*;

public class GalleryPanel extends JPanel {
//    public GalleryPanel(String name, double rating, String[] genres, int year) {
    public GalleryPanel(Movie movie) {
        super(new GridLayout(0,1));

//        setBounds(40, 80, 50, 100);
        setBackground(Color.lightGray);

        // temp border
        setBorder(BorderFactory.createBevelBorder(0));

        JLabel picture = new JLabel(new ImageIcon(movie.getImage()));
        add(picture);
        String title = movie.getTitle();
        if (movie.getTitle().length() > 40){
            title = title.substring(0,40) + " ...";
        }
        JLabel name = new JLabel(title);
        add(name);
        JLabel rating = new JLabel(String.valueOf(movie.getRating()));
        add(rating);
        JLabel genres = new JLabel(String.join(", ", movie.getGenres()));
        add(genres);
        JButton playButton = new JButton("Play Movie");
        add(playButton);
    }

}
