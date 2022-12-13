package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;

/*
Klassen GalleryPanel bruges til at oprette et panel, der indeholder data om et specifik Media-instans.
Det viser titlen, genren, ratingen, årstallet og et billede af mediet.
Derudover indeholder det en knap, der, når den trykkes på, åbner detalje-visningen af mediet i programmet.
*/
public class GalleryPanel extends JPanel {
    // media er den specifikke Media-instans, som skal vises i dette GalleryPanel-objekt
    // bliver ikke brugt, men er godt at ha
    private Media media;

    // konstruktøren for GalleryPanel-klassen,
    // konstruerer et panel der indeholder labels der indeholder data der er i Media-instansen
    public GalleryPanel(Media media) {
        // kalder JPanel-konstruktøren
        super();

        // gemmer den givne Media-instans i en lokal variabel
        this.media = media;

        // sætter layoutet for dette panel til at være en BoxLayout der sorterer dens elementer lodret
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // sætter baggrundsfarven for dette panel til at være lightGray
//        setBackground(Color.lightGray);

        // sætter en bevelBorder på dette panel
        setBorder(BorderFactory.createBevelBorder(1));

        // opretter et label med Media-objektets billede
        JLabel picture = new JLabel(new ImageIcon(media.getImage()));
        addToPanel(picture);

        // hvis titlen er længere end 25 tegn, bliver den forkortet og "..." tilføjet
        String title = media.getTitle();
        if (media.getTitle().length() > 25){
            title = title.substring(0,25) + " ...";
        }
        // opretter et label med titlen og gør den fed
        JLabel name = new JLabel(title);
        name.setFont(new Font("", Font.BOLD, 14));
        addToPanel(name);

        // opretter et label med Media-objektets genrer
        JLabel genres = new JLabel(String.join(", ", media.getGenres()));
        addToPanel(genres);

        // opretter et label med Media-objektets
        JLabel rating = new JLabel("Rating: " + media.getRating());
        addToPanel(rating);

        // opretter et label med Media-objektets årstal (hvis det er en serie er der 2 årstal)
        JLabel year = new JLabel(media.getYearString());
        addToPanel(year);

        // opretter en knap der går til "DetailsView" siden i programmet når man klikker på den
        JButton detailsButton = new JButton("Se Info");
        detailsButton.addActionListener(e -> {
            Main.goToDetailsView(media);
        });
        addToPanel(detailsButton);

        // opretter en favorit knap der tilføjer mediet til favorit listen hvis den bliver selected
        JCheckBox favoriteButton = new JCheckBox("Favorit");
        favoriteButton.addActionListener(e -> {
            if (favoriteButton.isSelected()){
                Main.addFavorite(media);
            } else {
                Main.removeFavorite(media);
            }
        });
        // hvis mediet er en favorit, vis det i UIet
        favoriteButton.setSelected(media.isFavorited());
        addToPanel(favoriteButton);
    }

    // Tilføjer et komponent, sætter dens alignment så den bliver vist i midten programmet,
    // og tilføjer den til GalleryPanel-objektet
    private void addToPanel(JComponent label){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }

}