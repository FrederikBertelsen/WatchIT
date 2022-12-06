package presentationLayer;

import domainLayer.DataBase;
import domainLayer.DataBaseImpl;
import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WatchItUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private Gallery gallery;
    private JTextField searchField;
    private ArrayList<SelectDropDown> dropdowns;


    // temp filter texts
    public String[] movie_show = new String[]{"Film", "Serier"};
    public String[] genres = new String[]{"Horror", "Action", "Romance"};
    public String[] ratings = new String[]{">0.0", ">6.0", ">7.0", ">8.0", ">9.0", ">9.5"};
    public String[] years = new String[]{"2020s", "2010s", "2000s", "1990s"};


    public WatchItUI(String windowTitle) {
        frame = new JFrame(windowTitle);

        // set up the top bar
        menuBar = new JMenuBar();
        // filters
        dropdowns = new ArrayList<>();
        MultiSelectDropDown movieShowMenu = new MultiSelectDropDown("Type", movie_show);
        menuBar.add(movieShowMenu);
        dropdowns.add(movieShowMenu);

        MultiSelectDropDown genreMenu = new MultiSelectDropDown("Genre", genres);
        menuBar.add(genreMenu);
        dropdowns.add(genreMenu);

        SingleSelectDropDown ratingMenu = new SingleSelectDropDown("Rating", ratings);
        menuBar.add(ratingMenu);
        dropdowns.add(ratingMenu);

        SingleSelectDropDown yearMenu = new SingleSelectDropDown("Årstal", years);
        menuBar.add(yearMenu);
        dropdowns.add(yearMenu);

        // search field and search button
        searchField = new JTextField();
        menuBar.add(searchField);
        JButton searchButton = new JButton("søg");
        menuBar.add(searchButton);

        frame.setJMenuBar(menuBar);

        // add gallery
        gallery = new Gallery();
        frame.add(new JScrollPane(gallery, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        frame.setLocationRelativeTo(null);
        frame.setSize(1920 / 2, 1080 / 2);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ArrayList<SelectDropDown> getDropDowns() {
        return dropdowns;
    }

    public void updateGallery(ArrayList<Media> medias){
        gallery.updatePanels(medias);

    }
}  