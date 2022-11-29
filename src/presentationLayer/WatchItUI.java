package presentationLayer;

import domainLayer.DataBase;
import domainLayer.DataBaseImpl;

import javax.swing.*;
import java.awt.*;

public class WatchItUI {
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JTextField searchField;

    private static String windowTitle = "WatchIt!";

    // filter texts
    public static String[] movie_show = new String[]{"Film", "Serier"};
    public static String[] genres = new String[]{"Horror", "Action", "Romance"};
    public static String[] ratings = new String[]{">0.0", ">6.0", ">7.0", ">8.0", ">9.0", ">9.5"};
    public static String[] years = new String[]{"2020s", "2010s", "2000s", "1990s"};

    public static void main(String[] args) {
        frame = new JFrame(windowTitle);

        // set up the top bar
        menuBar = new JMenuBar();
        addComponentsToTopBar();
        frame.setJMenuBar(menuBar);


        // add gallery
        GridLayout layout = new GridLayout(0, 5);
//        layout.setHgap(10);
        Gallery gallery = new Gallery(layout);
        frame.add(BorderLayout.CENTER, new JScrollPane(gallery, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        DataBase db = new DataBaseImpl();
        gallery.updatePanels(db.getMovies());


        frame.setSize(1920 / 2, 1080 / 2);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void addComponentsToTopBar() {

        // filters
        JMenu movieShowMenu = new MultiSelectDropDown("Type", movie_show);
        menuBar.add(movieShowMenu);
        JMenu genreMenu = new MultiSelectDropDown("Genre", genres);
        menuBar.add(genreMenu);
        JMenu ratingMenu = new SingleSelectDropDown("Rating", ratings);
        menuBar.add(ratingMenu);
        JMenu yearMenu = new SingleSelectDropDown("Årstal", years);
        menuBar.add(yearMenu);
        // search field and search button
        searchField = new JTextField();
        menuBar.add(searchField);
        JButton searchButton = new JButton("søg");
        menuBar.add(searchButton);


    }
}  