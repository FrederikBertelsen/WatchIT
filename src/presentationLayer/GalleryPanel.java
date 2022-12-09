package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;

public class GalleryPanel extends JPanel {

    private Media media;

    public GalleryPanel(Media media) {
        super();
        this.media = media;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        setBounds(40, 80, 50, 100);
        setBackground(Color.lightGray);

        // temp border
        setBorder(BorderFactory.createBevelBorder(0));

        JLabel picture = new JLabel(new ImageIcon(media.getImage()));
        addToPanel(picture);
        String title = media.getTitle();
        if (media.getTitle().length() > 25){
            title = title.substring(0,25) + " ...";
        }
        JLabel name = new JLabel(title);
        name.setFont(new Font("", Font.BOLD, 14));
        addToPanel(name);
        JLabel genres = new JLabel(String.join(", ", media.getGenres()));
        addToPanel(genres);
        JLabel rating = new JLabel("Rating: " + media.getRating());
        addToPanel(rating);
        JLabel year = new JLabel(media.getYearString());
        addToPanel(year);
        JButton detailsButton = new JButton("View Details");
        detailsButton.addActionListener(e -> {
            Main.goToDetailsView(media);
        });
        addToPanel(detailsButton);
    }

    private void addToPanel(JComponent label){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }

}
