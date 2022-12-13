package presentationLayer;

import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Movie;
import domainLayer.dataStructure.Playable;
import domainLayer.dataStructure.Show;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;

// denne klasse er et JPanel med nogle ekstra metoder og en konstruktør, for nemmere at kunne opdatere panelets indhold
public class DetailsView extends JPanel {

    private Media currentMedia;

    // labelerne der indeholder de informationer om det medie der bliver vist, herunder navn, billede, genre, rating og år
    private JLabel nameLabel;
    private JLabel pictureLabel;
    private JLabel genresLabel;
    private JLabel ratingLabel;
    private JLabel yearLabel;

    // check boxen så man kan sætte mediet til en favorit
    private JCheckBox favoriteButton;
    private ActionListener favoriteButtonActionListener;

    private JMenuBar seasonEpisodeMenu;
    private SingleSelectDropDown seasonDropDown;
    private SingleSelectDropDown episodeDropDown;
    private MenuListener episodeDropDownMenuListener;

    // variabler for knapperne "afspil" og "tilbage"
    private JButton playButton;
    private ActionListener playButtonActionListener;
    private JButton backButton;

    // Konstruktør for panelet der automatisk opretter de labels der kommer til at indeholde informationerne
    public DetailsView() {
        // Kald superklassens (JPanel) konstruktør
        super();
        // Sæt layoutet til at være en BoxLayout langs Y-aksen
        // det er så labelerne bliver vist lodret
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Sæt baggrundsfarven til hvid
        setBackground(Color.WHITE);

        // Opret en ny JLabel til at vise navnet på mediet
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("", Font.BOLD, 35));
        addToPanel(nameLabel);

        // Opret en ny JLabel til at vise billedet af mediet
        pictureLabel = new JLabel();
        addToPanel(pictureLabel);

        // Opret en ny JLabel til at vise genrerne for mediet
        genresLabel = new JLabel();
        genresLabel.setFont(new Font("", Font.PLAIN, 20));
        addToPanel(genresLabel);

        // Opret en ny JLabel til at vise ratingen for mediet
        ratingLabel = new JLabel();
        ratingLabel.setFont(new Font("", Font.PLAIN, 20));
        addToPanel(ratingLabel);

        // Opret en ny JLabel til at vise udgivelsesår (og slutår hvis mediet er en serie) af mediet
        yearLabel = new JLabel();
        yearLabel.setFont(new Font("", Font.PLAIN, 20));
        addToPanel(yearLabel);

        // opretter en favorit knap der tilføjer mediet til favorit listen hvis den bliver selected
        favoriteButton = new JCheckBox("Favorit");
        // Sæt fonten for genrelabelen til at være normal og størrelse 15
        yearLabel.setFont(new Font("", Font.PLAIN, 15));
        addToPanel(favoriteButton);

        // opretter sæson og episode dropdown, så man kan vælge hvilken episode man vil se.
        seasonEpisodeMenu = new JMenuBar();
        seasonDropDown = new SingleSelectDropDown("Sæson", new String[]{"-"});
        seasonEpisodeMenu.add(seasonDropDown);

        episodeDropDown = new SingleSelectDropDown("Episode", new String[]{"-"});
        seasonEpisodeMenu.add(episodeDropDown);
        episodeDropDown.addMenuListener(episodeDropDownMenuListener);

        addToPanel(seasonEpisodeMenu);

        // Opret en ny JButton med teksten "Afspil" (med nogle ekstra mellemrum for at gøre klappen større)
        playButton = new JButton("    Afspil    ");
        playButton.setFont(new Font("", Font.PLAIN, 40));
        addToPanel(playButton);

        // Opret en ny JButton med teksten "Tilbage" (med nogle ekstra mellemrum for at gøre klappen større)
        backButton = new JButton("   Tilbage   ");
        backButton.setFont(new Font("", Font.PLAIN, 40));
        // Tilføj en ActionListener til knappen, der går tilbage til galleri-visningen, når knappen bliver trykket på
        backButton.addActionListener(e -> {
            Main.goToGalleryView();
        });
        addToPanel(backButton);
    }

    // Opdaterer informationen om Media-objektet på panelet.
    public void update(Media media) {
        currentMedia = media;

        // Sætter navnet på Media-objektet i JLabel-objektet.
        nameLabel.setText(media.getTitle());

        // Skaler billedet for Media-objektet til at være dobbelt så stort.
        Image scaledImage = media.getImage().getScaledInstance(media.getImage().getWidth() * 2, media.getImage().getHeight() * 2, Image.SCALE_FAST);
        // Sætter billedet for Media-objektet i JLabel-objektet.
        pictureLabel.setIcon(new ImageIcon(scaledImage));

        // Sætter genrer for Media-objektet i JLabel-objektet.
        genresLabel.setText(String.join(", ", media.getGenres()));

        // Sætter rating for Media-objektet i JLabel-objektet.
        ratingLabel.setText("Rating: " + media.getRating());

        // Sætter udgivelsesår for Media-objektet i JLabel-objektet.
        yearLabel.setText(media.getYearString());

        // Sætter favorit knappens klik metode
        favoriteButton.removeActionListener(favoriteButtonActionListener);
        favoriteButtonActionListener = (e -> {
            if (favoriteButton.isSelected()) {
                Main.addFavorite(media);
            } else {
                Main.removeFavorite(media);
            }
        });
        favoriteButton.addActionListener(favoriteButtonActionListener);
        // hvis mediet er en favorit, vis det i UIet
        favoriteButton.setSelected(media.isFavorited());


        if (media instanceof Show) {
            Show show = (Show) media;
            seasonEpisodeMenu.setVisible(true);

            String[] seasons = new String[show.getSeasonCount()];
            for (int i = 0; i < show.getSeasonCount(); i++) {
                seasons[i] = String.valueOf(i + 1);
            }
            seasonDropDown.changeMenuItems(seasons);

            episodeDropDown.removeMenuListener(episodeDropDownMenuListener);
            episodeDropDownMenuListener = (new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    if (seasonDropDown.getSelected().equals("")) return;

                    int seasonNumber = Integer.parseInt(seasonDropDown.getSelected());
                    int episodecount = show.getSeasonEpisodeCount(seasonNumber);

                    if (episodeDropDown.getItemCount() != episodecount){
                        String[] episodes = new String[episodecount];
                        for (int i = 0; i < episodecount; i++) {
                            episodes[i] = String.valueOf(i + 1);
                        }
                        episodeDropDown.changeMenuItems(episodes);
                    }
                }

                @Override
                public void menuDeselected(MenuEvent e) {
                }

                @Override
                public void menuCanceled(MenuEvent e) {
                }
            });

            episodeDropDown.addMenuListener(episodeDropDownMenuListener);
        } else {
            seasonEpisodeMenu.setVisible(false);

        }


        // Tilføjer en ny ActionListener til "Afspil" knappen så den afspiller den rigtige film når der bliver trykket på knappen.
        playButton.removeActionListener(playButtonActionListener);
        playButtonActionListener = (e -> {
            Main.play(currentMedia, getCurrentPlayable());
        });
        playButton.addActionListener(playButtonActionListener);
    }

    // denne metode er lavet at man ikke skal sætte alignment for vært component man vil tilføje
    private void addToPanel(JComponent label) {
        // Sætter alignment af JComponent-objektet til midden af programmet.
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Tilføjer componenten
        add(label);
    }

    private Playable getCurrentPlayable(){
        if (currentMedia instanceof Show){
            Show show = (Show)currentMedia;

            int seasonNumber = Integer.parseInt(seasonDropDown.getSelected());
            int episodeNumber = Integer.parseInt(episodeDropDown.getSelected());

            return show.getSeason(seasonNumber).getEpisode(episodeNumber);

        } else if (currentMedia instanceof Movie){
            return (Movie)currentMedia;
        }
        return null;
    }
}
