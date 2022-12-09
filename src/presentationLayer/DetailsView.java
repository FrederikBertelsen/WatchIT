package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;

public class DetailsView extends JPanel {
    public DetailsView() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        setBounds(40, 80, 50, 100);
        setBackground(Color.lightGray);

//        JLabel picture = new JLabel(new ImageIcon(media.getImage()));
//        addToPanel(picture);
        String title = "The Fat Batman"; //media.getTitle();
//        if (media.getTitle().length() > 30){
//            title = title.substring(0,30) + " ...";
//        }
        JLabel name = new JLabel(title);
        add(name, BorderLayout.PAGE_START);
        JLabel rating = new JLabel(String.valueOf(10));
        add(rating, BorderLayout.CENTER);
        JLabel genres = new JLabel("Horror, Romance, Sci-fi, Triller");
        add(genres, BorderLayout.CENTER);
        JButton detailsButton = new JButton("--- Play ---");
        add(detailsButton, BorderLayout.CENTER);
    }

    public void update(Media media) {
        for (Component component : getComponents()) {
            remove(component);
        }
        JLabel name = new JLabel(media.getTitle());
        name.setFont(new Font("", Font.BOLD, 35));
        addToPanel(name);

        Image scaledImage = media.getImage().getScaledInstance(media.getImage().getWidth() * 2, media.getImage().getHeight()*2, Image.SCALE_FAST);
        JLabel picture = new JLabel(new ImageIcon(scaledImage));
        addToPanel(picture);

//        JLabel spacer = new JLabel("     ");
//        spacer.setFont(new Font("", Font.PLAIN, 40));
//        addToPanel(spacer);

        JLabel genres = new JLabel(String.join(", ", media.getGenres()));
        genres.setFont(new Font("", Font.PLAIN, 25));
        addToPanel(genres);

        JLabel rating = new JLabel("Rating: " + media.getRating());
        rating.setFont(new Font("", Font.PLAIN, 25));
        addToPanel(rating);

        JLabel year = new JLabel(media.getYearString());
        addToPanel(year);
        year.setFont(new Font("", Font.PLAIN, 25));

//        JLabel spacer1 = new JLabel("      ");
//        spacer1.setFont(new Font("", Font.PLAIN, 60));
//        addToPanel(spacer1);

        JButton playButton = new JButton("    Afspil    ");
        playButton.setFont(new Font("", Font.PLAIN, 40));
        addToPanel(playButton);

        JButton backButton = new JButton("   Tilbage   ");
        backButton.setFont(new Font("", Font.PLAIN, 40));
        addToPanel(backButton);

//        JButton backButton = new JButton("Tilbage");
//        add(backButton, BorderLayout.CENTER);

    }

    private void addToPanel(JComponent label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }
}
