package domainLayer;

import dataLayer.DataHandler;
import dataLayer.DataHandlerImpl;
import dataLayer.FavoriteAddRemoveException;
import domainLayer.dataStructure.*;
import presentationLayer.DialogCreator;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class DataBaseImpl implements DataBase {
    // arrayet der indeholder alle film
    private Movie[] movies;
    // arrayet der indeholder alle serier
    private Show[] shows;
    // Et DataHandler-objekt til at håndtere film data og fil
    private DataHandler movieDataHandler;
    // Stien til filen med film data
    private String moviePath = "data/film.txt";
    // Stien til mappen med billeder af filmplakater
    private String movieImageFolderPath = "data/filmplakater";

    // Et DataHandler-objekt til at håndtere serier data og fil
    private DataHandler showDataHandler;
    // Stien til filen med serie data
    private String showPath = "data/serier.txt";
    // Stien til mappen med billeder af serieforsider
    private String showImageFolderPath = "data/serieforsider";


    private DataHandler favoritesDataHandler;
    private String favoritesPath = "data/favorites.txt";
    private HashSet<String> favoritesSet;

    // Konstruktør, som initialiserer DataHandler-objekterne og indlæser film og serie data
    public DataBaseImpl() {
        // Opretter DataHandler-objekterne med de angivne stier
        movieDataHandler = new DataHandlerImpl(moviePath, movieImageFolderPath);
        showDataHandler = new DataHandlerImpl(showPath, showImageFolderPath);

        favoritesDataHandler = new DataHandlerImpl(favoritesPath, true);

        try {
            // indlæser listen af favoriter i et HashSet, så favoritter hurtigt kan checkes
            favoritesSet = new HashSet<>(favoritesDataHandler.loadData());

            // Indlæser data om film ved hjælp af movieDataHandler
            // og konvertere dataen til Movie-objekter
            movies = movieSerializer(movieDataHandler.loadData(), favoritesSet);
            // Indlæser data om tv-serier ved hjælp af showDataHandler
            // og konvertere dataen til Series-objekter
            shows = showSerializer(showDataHandler.loadData(), favoritesSet);


        } catch (FileNotFoundException e) {
            // Fejlmeddelelse, hvis filen med data om film eller tv-serier ikke findes
            DialogCreator.createExceptionDialog("Fejl ved indlæsning af film og serie filer: /n" + e.getMessage());
            System.out.println();
        } catch (IOException e) {
            // Fejlmeddelelse, hvis der opstår en fejl ved indlæsning af billeder af filmplakater eller serieforsider
            DialogCreator.createExceptionDialog("Fejl ved indlæsning af film og serie billeder: " + e.getMessage());
        }

    }

    // Getter for arrayet med filmene
    @Override
    public Movie[] getMovies() {
        return movies;
    }

    // Getter for arrayet med serierne
    @Override
    public Show[] getShows() {
        return shows;
    }

    @Override
    public Movie[] movieSerializer(ArrayList<String> moviesStrings, HashSet<String> favorites) throws IOException {

        // Opret en ny liste med Movie-objekter med samme størrelse som input-listen
        Movie[] output = new Movie[moviesStrings.size()];
        // For hver streng i input-listen, opret et nyt Movie-objekt baseret på værdierne i Strengen
        for (int i = 0; i < moviesStrings.size(); i++) {
            // Del Strengen op i en liste med forskellige dele af værdien adskilt af ";" eller "; "
            String[] stringParts = moviesStrings.get(i).split("; ?");

            // Den første del af værdien er showets titel
            String title = stringParts[0];

            // Den anden del af værdien er showets udgivelsesår
            int year = Integer.parseInt(stringParts[1]);

            // Den tredje del er genre(r).
            // Splitter strengen med alle genre, og sætter dem i et String Array.
            String[] genres = stringParts[2].split(", ");

            // Den fjerde del af værdien er showets rating
            // Erstatter eventuelle kommaer med punktum, så værdien kan parses til et double
            stringParts[3] = stringParts[3].replace(",", ".");
            double rating = Double.parseDouble(stringParts[3]);

            //Finder tilhørende billede ud fra filmens titel,
            BufferedImage image = movieDataHandler.getImage(title);
            if (image == null) {
                System.out.println("Image for '" + title + "' not found!");
            }

            boolean isFavorite = favorites.contains(title);
            // opretter et Movie-objekt med den konverterede data.
            Movie movie = new Movie(title, year, genres, rating, image, isFavorite);
            // tilføj det nye objekt til databasens Movie-array
            output[i] = movie;
        }

        return output;
    }
    @Override
    public Movie[] movieSerializer(ArrayList<String> moviesStrings) throws IOException {
        return movieSerializer(moviesStrings, new HashSet<>());
    }
    @Override
    public Show[] showSerializer(ArrayList<String> showStrings, HashSet<String> favorites) throws IOException {
        // Opret en ny liste med Show-objekter med samme størrelse som input-listen
        Show[] output = new Show[showStrings.size()];
        // For hver streng i input-listen, opret et nyt Show-objekt baseret på værdierne i Strengen
        for (int i = 0; i < showStrings.size(); i++) {
            // Del Strengen op i en liste med forskellige dele af værdien adskilt af ";" eller "; "
            String[] stringParts = showStrings.get(i).split("; ?");

            // Den første del af værdien er showets titel
            String title = stringParts[0];

            // Den anden del af værdien er showets udgivelsesår og slutår
            String[] years = stringParts[1].split(" ?- ?");
            // Det første år er udgivelsesåret
            int releaseYear = Integer.parseInt(years[0].strip());
            int toYear = 0;
            // Det andet år er slutåret. Men kun hvis serien er sluttet
            if (years.length > 1) toYear = Integer.parseInt(years[1].strip());

            // Den tredje del er genre(r).
            // Splitter strengen med alle genre, og sætter dem i et String Array.
            String[] genres = stringParts[2].split(", ?");

            // Den fjerde del af værdien er showets rating
            // Erstatter eventuelle kommaer med punktum, så værdien kan parses til et double
            stringParts[3] = stringParts[3].replace(",", ".");
            double rating = Double.parseDouble(stringParts[3]);

            // Den femte del af værdien er showets sæsoner
            String[] seasonStrings = stringParts[4].split(", ?");
            Season[] seasons = new Season[seasonStrings.length];
            // For hver sæson i showet, opret et nyt Season-objekt baseret på værdierne i strengen
            for (int j = 0; j < seasonStrings.length; j++) {
                // Del værdien op i en liste med forskellige dele af værdien adskilt af "-"
                String[] seasonStringParts = seasonStrings[j].split("-");
                // Den første del af strengen er sæsonens nummer
                int seasonNumber = Integer.parseInt(seasonStringParts[0].strip());
                // Den anden del er antallet af episoder i sæsonen
                int episodeCount = Integer.parseInt(seasonStringParts[1].strip());
                // Opret et nyt array med "Episode" objekter med samme størrelse som antallet af episoder,
                // der kan blive fyldt med sæsonens episoder
                Episode[] episodes = new Episode[episodeCount];
                // opret et Episode-object med episode nummer, og indsæt Episode-Objektet i sæsonens episode-array
                for (int k = 0; k < episodeCount; k++) {
                    Episode newEpisode = new Episode(seasonNumber, k+1);
                    episodes[k] = newEpisode;
                }
                // Opret et nyt Season-objekt med sæson nummer og episode-array
                Season newSeason = new Season(seasonNumber, episodes);
                // tilføj det nye Season-object i showets sæson array
                seasons[j] = newSeason;
            }

            //Finder tilhørende billede ud fra seriens titel,
            BufferedImage image = showDataHandler.getImage(title);
            if (image == null) {
                System.out.println("Image for '" + title + "' not found!");
            }

            boolean isFavorite = favorites.contains(title);
            // opretter et Show-objekt med den konverterede data.
            Show show = new Show(title, releaseYear, toYear, genres, rating, seasons, image, isFavorite);
            // tilføj det nye objekt til databasens Show-array
            output[i] = show;
        }

        return output;

    }
    public Show[] showSerializer(ArrayList<String> showStrings) throws IOException {
        return showSerializer(showStrings, new HashSet<>());
    }
    @Override
    public ArrayList<Media> getFilteredMedia(SearchPreset searchPreset) {
        // Opret en liste med media, som matcher de angivne typer (hvis ingen typer er valgt, bliver alle medier taget med)
        ArrayList<Media> output = getMediaByType(searchPreset.getTypes());

        // Filtrer listen med media, så den kun indeholder favoritter (hvis brugeren kun vil se favoritter)
        if (searchPreset.FavoritesOnly()){
            output = filterByFavoritesOnly(output);
        }

        // Filtrer listen med media, så den kun indeholder de angivne genrer
        output = filterByGenre(output, searchPreset.getGenres());
        // Hvis der er angivet en rating, så filtrer listen med media, så den kun indeholder media med den angivne rating eller højere
        if (!searchPreset.getRatingString().equals("")) {
            double ratingDouble = Double.parseDouble(searchPreset.getRatingString().substring(1));
            output = filterByRating(output, ratingDouble);
        }
        // Hvis der er angivet et årsinterval, så filtrer listen med medier, så den kun indeholder media fra det angivne årsinterval
        if (!searchPreset.getYearsString().equals("")) {
            String[] yearParts = searchPreset.getYearsString().split("-");
            int yearFrom = Integer.parseInt(yearParts[0].strip());
            int yearTo = Integer.parseInt(yearParts[1].strip());
            output = filterByYear(output, yearFrom, yearTo);
        }
        // Hvis der er angivet et søgeord, så filtrer listen med media, så den kun indeholder media, hvor media-titlen matcher søgeordet tilpas nok (se StringMatcher klassen)
        // StringMatcher klasses sorterer også medierne efter mediernes match-score
        if (!searchPreset.getSearchTerm().equals("")) {
            output = StringMatcher.getMatches(searchPreset.getSearchTerm(), output);
        }
        // Hvis der ikke er angivet et søgeterm, så brug det valgte sorteringsmåde.
        // Det er så listen med medier ikke bliver sorteret mere end en gang hver gang metoden kører
        else {
            // Hvis der er angivet en sorteringsmåde, så sorter listen med media efter den angivne måde
            if (!searchPreset.getSortBy().equals("")) {
                switch (searchPreset.getSortBy()) {
                    case "Titel" -> output.sort(Comparator.comparing(Media::getTitle));
                    case "Rating" -> output.sort(Comparator.comparing(Media::getRating));
                    case "Årstal" -> output.sort(Comparator.comparing(Media::getYear));
                }
            }

            // Hvis der er angivet en sorteringsretning, så vend rækkefølgen af listen med media, hvis retningen er "faldende"
            if (searchPreset.getSortByDirection().equals("Faldende")) {
                Collections.reverse(output);
            }
        }

        // Returner den filtrerede og sorterede liste med media
        return output;
    }

    @Override
    public ArrayList<Media> getMediaByType(HashSet<String> types) {
        ArrayList<Media> output = new ArrayList<>();
        // hvis ingen type er valgt, tilføj alle film og serier
        if (types.size() == 0) {
            Collections.addAll(output, getMovies());
            Collections.addAll(output, getShows());
        }
        // hvis typen "Film" er valgt, tilføj alle film
        else if (types.contains("Film")) {
            Collections.addAll(output, getMovies());
        }
        // hvis typen "Serier" er valgt, tilføj alle serier
        else if (types.contains("Serier")) {
            Collections.addAll(output, getShows());
        }

        return output;
    }

    @Override
    public ArrayList<Media> filterByGenre(ArrayList<Media> mediaList, HashSet<String> genres) {
        // hvis ingen genre er valgt, skal ingen medier sorteres fra.
        if (genres.size() == 0) return mediaList;

        ArrayList<Media> output = new ArrayList<>();

        // for hver medie, hvis mediet indeholder alle de valgte genre bliver det tilføjet til output-listen
        for (Media media : mediaList) {
            boolean match = true;
            for (String genre : genres) {
                if (!media.getGenres().contains(genre)) {
                    match = false;
                    break;
                }
            }
            // hvis mediet indeholder alle de valgte genre
            if (match) {
                output.add(media);
            }
        }

        return output;
    }

    @Override
    public ArrayList<Media> filterByRating(ArrayList<Media> mediaList, double rating) {
        ArrayList<Media> output = new ArrayList<>();

        // for hver medie, hvis mediets rating er højere eller det samme som den valgte rating, tilføjes mediet til output-listen
        for (Media media : mediaList) {
            if (media.getRating() >= rating) {
                output.add(media);
            }
        }

        return output;
    }

    @Override
    public ArrayList<Media> filterByYear(ArrayList<Media> mediaList, int yearStart, int yearEnd) {
        ArrayList<Media> output = new ArrayList<>();

        // Hvis mediets udgivelse er inden for det angivne interval, skal det tilføjes til output-listen
        for (Media media : mediaList) {
            if (yearStart <= media.getYear() && media.getYear() <= yearEnd) {
                output.add(media);
            }
        }

        return output;
    }
    @Override
    public ArrayList<Media> filterByFavoritesOnly(ArrayList<Media> mediaList) {
        ArrayList<Media> output = new ArrayList<>();

        // for hver medie, hvis mediets er en favorit, tilføjes mediet til output-listen
        for (Media media : mediaList) {
            if (media.isFavorited()) {
                output.add(media);
            }
        }

        return output;
    }

    @Override
    public void addToFavorites(Media media) {
        media.setFavorite(true);
        try {
            favoritesDataHandler.addFromFile(media.getTitle());
        } catch (FavoriteAddRemoveException | IOException e) {
            DialogCreator.createWarningDialog(e.getMessage());
        }
    }

    @Override
    public void removeFromFavorites(Media media) {
        media.setFavorite(false);
        try {
            favoritesDataHandler.removeFromFile(media.getTitle());
        } catch (FavoriteAddRemoveException | IOException e) {
            DialogCreator.createWarningDialog(e.getMessage());
        }
    }
}
