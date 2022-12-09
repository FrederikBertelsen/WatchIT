package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

public class WatchItUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private Gallery gallery;
    private JScrollPane galleryScrollPane;
    private DetailsView detailsView;

    private JTextField searchField;
    private MultiSelectDropDown typeDropDown;
    private MultiSelectDropDown genreDropDown;
    private SingleSelectDropDown ratingDropDown;
    private SingleSelectDropDown yearDropDown;

    private SingleSelectDropDown sortByDropDown;
    private SingleSelectDropDown sortByDirectionDropDown;


    // temp filter texts
    public String[] movie_show = new String[]{"Film", "Serier"};
    public String[] genres = new String[]{"Action", "Adventure", "Biography", "Comedy", "Crime", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mystery", "Romance", "Sci-fi", "Sport", "Thriller", "War", "Western"};
    public String[] ratings = new String[]{">5.0", ">6.0", ">7.0", ">8.0", ">9.0", ">9.5"};
    public String[] years = new String[]{"2010-2020", "2000-2010", "1990-2000", "1980-1990", "1970-1980", "1960-1970", "1950-1960", "1940-1950", "1930-1940", "       0-1930"};
    public String[] sortBy = new String[]{"Titel", "Rating", "Årstal"};
    public String[] sortByDirections = new String[]{"Stigende", "Faldende"};


    public WatchItUI(String windowTitle) {
        frame = new JFrame(windowTitle);

        // set up the top bar
        menuBar = new JMenuBar();
        // filters
        MultiSelectDropDown movieShowMenu = new MultiSelectDropDown("Type", movie_show);
        menuBar.add(movieShowMenu);
        typeDropDown = movieShowMenu;

        MultiSelectDropDown genreMenu = new MultiSelectDropDown("Genre", genres);
        menuBar.add(genreMenu);
        genreDropDown = genreMenu;

        SingleSelectDropDown ratingMenu = new SingleSelectDropDown("Rating", ratings);
        menuBar.add(ratingMenu);
        ratingDropDown = ratingMenu;

        SingleSelectDropDown yearMenu = new SingleSelectDropDown("Årstal", years);
        menuBar.add(yearMenu);
        yearDropDown = yearMenu;

        SingleSelectDropDown sortByMenu = new SingleSelectDropDown("Sorter Efter", sortBy);
        // sort by title by default
        sortByMenu.getItem(1).getModel().setSelected(true);
        menuBar.add(sortByMenu);
        sortByDropDown = sortByMenu;

        SingleSelectDropDown sortByDirectionMenu = new SingleSelectDropDown("Sorter Retning", sortByDirections);
        // sort by Descending by default
        sortByDirectionMenu.getItem(1).getModel().setSelected(true);
        menuBar.add(sortByDirectionMenu);
        sortByDirectionDropDown = sortByDirectionMenu;

        // reset button
        JButton resetButton = new JButton("Nulstil filtre");
        resetButton.addActionListener(e -> {
            resetFilters();
        });
        menuBar.add(resetButton);


        // search field and search button
        searchField = new JTextField();
        menuBar.add(searchField);
        JButton searchButton = new JButton("søg");
        searchButton.addActionListener(e -> {
            updateGallery();
        });
        menuBar.add(searchButton);
        frame.getRootPane().setDefaultButton(searchButton);


        frame.setJMenuBar(menuBar);

        // create and add gallery
        gallery = new Gallery();
        galleryScrollPane = new JScrollPane(gallery, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(galleryScrollPane);

        // create details view
        detailsView = new DetailsView();

//        frame.setLocationRelativeTo(null);
        frame.setSize(1920 / 2, 1080 / 2);
        frame.setVisible(true);
//        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void goToDetailsView(Media media){
        frame.remove(galleryScrollPane);

        detailsView.update(media);

        frame.add(detailsView);

        frame.setVisible(false);
        frame.setVisible(true);
    }
    public void goToGalleryView(){
        frame.remove(detailsView);

        frame.add(galleryScrollPane);

        frame.setVisible(false);
        updateGallery();
    }


    public void updateGallery() {
        ArrayList<Media> medias = Main.db.filterMedia(getSelectedTypes(), getSelectedGenres(), getSelectedRating(), getSelectedYear(), getSelectedSortBy(), getSelectedSortByDirection(), getSearchTerm());
        gallery.updatePanels(medias);
        frame.setVisible(true);
    }

    private void resetFilters() {
        searchField.setText("");

        typeDropDown.resetSelected();
        genreDropDown.resetSelected();
        ratingDropDown.resetSelected();
        yearDropDown.resetSelected();
        sortByDropDown.resetSelected();
        sortByDirectionDropDown.resetSelected();

        updateGallery();
    }


    public HashSet<String> getSelectedTypes() {
        return typeDropDown.getSelected();
    }

    public HashSet<String> getSelectedGenres() {
        return genreDropDown.getSelected();
    }

    public String getSelectedRating() {
        return ratingDropDown.getSelected();
    }

    public String getSelectedYear() {
        return yearDropDown.getSelected();
    }

    public String getSelectedSortBy() {
        return sortByDropDown.getSelected();
    }

    public String getSelectedSortByDirection() {
        return sortByDirectionDropDown.getSelected();
    }

    public String getSearchTerm() {
        return searchField.getText();
    }
}