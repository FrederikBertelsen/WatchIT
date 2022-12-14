package presentationLayer;

import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Playable;
import domainLayer.dataStructure.Show;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class VideoPlayer extends JFrame {
    private Media media;
    private Playable playable;

    public VideoPlayer(Media media, Playable playable){
        super(media.getTitle() + "   '" + "/FAKE PATH/" + playable.getPath() + "'");
        this.media = media;
        this.playable = playable;

        if (media instanceof Show) setTitle(media.getTitle() + "   '" + "/FAKE PATH/" + media.getTitle() + "/" + playable.getPath() + "'");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.BLACK);

        Image scaledImage = media.getImage().getScaledInstance(media.getImage().getWidth() * 4, media.getImage().getHeight() * 2, Image.SCALE_FAST);

        addToPanel(new JLabel(new ImageIcon(scaledImage)));


        JLabel iconLabel = new JLabel("⏮    ⏵    ⏭        ⛶");
        iconLabel.setFont(new Font("",Font.BOLD,50));
        iconLabel.setForeground(Color.WHITE);
        addToPanel(iconLabel);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setVisible(true);
    }

    // denne metode er lavet at man ikke skal sætte alignment for vært component man vil tilføje
    private void addToPanel(JComponent label) {
        // Sætter alignment af JComponent-objektet til midden af programmet.
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Tilføjer componenten
        getContentPane().add(label);
    }
}
