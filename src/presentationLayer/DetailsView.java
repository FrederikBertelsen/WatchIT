package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;

// denne klasse er et JPanel med nogle ekstra metoder og en konstruktør, for nemmere at kunne opdatere panelets indhold
public class DetailsView extends JPanel {
    // labelerneF der indeholder de informationer om det medie der bliver vist, herunder navn, billede, genre, rating og år
    private JLabel name;
    private JLabel picture;
    private JLabel genres;
    private JLabel rating;
    private JLabel year;
    // variabler for knapperne "afspil" og "tilbage"
    private JButton playButton;
    private JButton backButton;

    // Konstruktør for panelet der automatisk opretter de labels der kommer til at indeholde informationerne
    public DetailsView() {
        // Kald superklassens (JPanel) konstruktør
        super();
        // Sæt layoutet til at være en BoxLayout langs Y-aksen
        // det er så labelerne bliver vist lodret
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Sæt baggrundsfarven til lysegrå
        setBackground(Color.lightGray);

        // Opret en ny JLabel til at vise navnet på mediet
        name = new JLabel();
        // Sæt fonten for navnelabelen til at være fed og størrelse 35
        name.setFont(new Font("", Font.BOLD, 35));
        // Tilføj navnelabelen til panelet
        addToPanel(name);

        // Opret en ny JLabel til at vise billedet af mediet
        picture = new JLabel();
        // Tilføj billedlabelen til panelet
        addToPanel(picture);

        // Opret en ny JLabel til at vise genrerne for mediet
        genres = new JLabel();
        // Sæt fonten for genrelabelen til at være normal og størrelse 20
        genres.setFont(new Font("", Font.PLAIN, 20));
        // Tilføj genrelabelen til panelet
        addToPanel(genres);

        // Opret en ny JLabel til at vise ratingen for mediet
        rating = new JLabel();
        // Sæt fonten for genrelabelen til at være normal og størrelse 20
        rating.setFont(new Font("", Font.PLAIN, 20));
        // Tilføj ratinglabelen til panelet
        addToPanel(rating);

        // Opret en ny JLabel til at vise udgivelsesår (og slutår hvis mediet er en serie) af mediet
        year = new JLabel();
        // Sæt fonten for genrelabelen til at være normal og størrelse 20
        year.setFont(new Font("", Font.PLAIN, 20));
        // Tilføj årstal-labelen til panelet
        addToPanel(year);

        // Opret en ny JButton med teksten "Afspil" (med nogle ekstra mellemrum for at gøre klappen større)
        playButton = new JButton("    Afspil    ");
        // Sæt fonten for knappen til at være normal og størrelse 40
        playButton.setFont(new Font("", Font.PLAIN, 40));
        // Tilføj knappen til panelet
        addToPanel(playButton);

        // Opret en ny JButton med teksten "Tilbage" (med nogle ekstra mellemrum for at gøre klappen større)
        backButton = new JButton("   Tilbage   ");
        backButton.setFont(new Font("", Font.PLAIN, 40));
        // Tilføj en ActionListener til knappen, der går tilbage til galleri-visningen, når knappen bliver trykket på
        backButton.addActionListener(e -> {
            Main.goToGalleryView();
        });
        // Tilføj knappen til panelet
        addToPanel(backButton);
    }

    // Opdaterer informationen om Media-objektet på panelet.
    public void update(Media media) {
        // Sætter navnet på Media-objektet i JLabel-objektet.
        name.setText(media.getTitle());

        // Skaler billedet for Media-objektet til at være dobbelt så stort.
        Image scaledImage = media.getImage().getScaledInstance(media.getImage().getWidth() * 2, media.getImage().getHeight()*2, Image.SCALE_FAST);
        // Sætter billedet for Media-objektet i JLabel-objektet.
        picture.setIcon(new ImageIcon(scaledImage));

        // Sætter genrer for Media-objektet i JLabel-objektet.
        genres.setText(String.join(", ", media.getGenres()));

        // Sætter rating for Media-objektet i JLabel-objektet.
        rating.setText("Rating: " + media.getRating());

        // Sætter udgivelsesår for Media-objektet i JLabel-objektet.
        year.setText(media.getYearString());

        // Tilføjer en ny ActionListener til "Afspil" knappen så den afslipper den rigtige film når der bliver trykket på knappen.
        playButton.addActionListener(e -> {
            Main.play(media);
        });
    }

    // denne metode er lavet at man ikke skal sætte aligment for vært component man vil tilføje
    private void addToPanel(JComponent label) {
        // Sætter alignment af JComponent-objektet til midden af programmet.
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Tilføjer componenten
        add(label);
    }
}
