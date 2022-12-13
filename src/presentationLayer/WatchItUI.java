package presentationLayer;

import domainLayer.SearchPreset;
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
    private JCheckBox favoritesOnly;
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
        typeDropDown = new MultiSelectDropDown("Type", movie_show);
        menuBar.add(typeDropDown);

        genreDropDown = new MultiSelectDropDown("Genre", genres);
        menuBar.add(genreDropDown);

        ratingDropDown = new SingleSelectDropDown("Rating", ratings);
        menuBar.add(ratingDropDown);

        yearDropDown = new SingleSelectDropDown("Årstal", years);
        menuBar.add(yearDropDown);

        sortByDropDown = new SingleSelectDropDown("Sorter Efter", sortBy);
        // sort by title, by default
        sortByDropDown.getItem(1).getModel().setSelected(true);
        menuBar.add(sortByDropDown);

        sortByDirectionDropDown = new SingleSelectDropDown("Sorter Retning", sortByDirections);
        // sort by Descending, by default
        sortByDirectionDropDown.getItem(1).getModel().setSelected(true);
        menuBar.add(sortByDirectionDropDown);

        // search only favorites
        favoritesOnly = new JCheckBox("Kun favorit");
        menuBar.add(favoritesOnly);

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
        // når brugeren klikker 'ENTER' i programmet, klikker programmet på søge knappen
        frame.getRootPane().setDefaultButton(searchButton);

        frame.setJMenuBar(menuBar);

        // create and add gallery
        gallery = new Gallery();
        galleryScrollPane = new JScrollPane(gallery, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(galleryScrollPane);

        // create details view
        detailsView = new DetailsView();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150, 800);
//        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

        frame.setVisible(true);
        updateGallery();
    }


    public void updateGallery() {
        SearchPreset searchPreset = new SearchPreset(getSelectedTypes(), getSelectedGenres(), getSelectedRating(), getSelectedYear(), getSelectedSortBy(), getSelectedSortByDirection(), getSearchTerm(), FavoritesOnly());
        ArrayList<Media> foundMedia = Main.filterMedia(searchPreset);
        gallery.updatePanels(foundMedia);
        frame.setVisible(true);
    }

    private void resetFilters() {
        searchField.setText("");
        favoritesOnly.setSelected(false);

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

    public boolean FavoritesOnly(){
        return favoritesOnly.isSelected();
    }
}