package presentationLayer;

import domainLayer.dataStructure.Movie;

import javax.swing.*;
import java.awt.*;

public class GalleryPanel extends JPanel {
    public GalleryPanel(Movie movie) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        setBounds(40, 80, 50, 100);
        setBackground(Color.lightGray);

        // temp border
        setBorder(BorderFactory.createBevelBorder(0));

        JLabel picture = new JLabel(new ImageIcon(movie.getImage()));
        addToPanel(picture);
        String title = movie.getTitle();
        if (movie.getTitle().length() > 30){
            title = title.substring(0,30) + " ...";
        }
        JLabel name = new JLabel(title);
        addToPanel(name);
        JLabel rating = new JLabel(String.valueOf(movie.getRating()));
        addToPanel(rating);
        JLabel genres = new JLabel(String.join(", ", movie.getGenres()));
        addToPanel(genres);
        JButton detailsButton = new JButton("View Details");
        add(detailsButton);
    }

    public void addToPanel(JLabel label){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }

}
