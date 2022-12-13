package domainLayer;

import java.util.HashSet;

// Denne klasse bliver brugt til at samle alle de forskellige søge specifikationer brugeren har valgt
public class SearchPreset {
    private HashSet<String> types;
    private HashSet<String> genres;
    private String ratingString;
    private String yearsString;
    private String sortBy;
    private String sortByDirection;
    private String searchTerm;
    private boolean favoritesOnly;


    public SearchPreset(HashSet<String> types, HashSet<String> genres, String rating, String years, String sortBy, String sortByDirection, String searchTerm, boolean favoritesOnly) {
        this.types = types;
        this.genres = genres;
        this.ratingString = rating;
        this.yearsString = years;
        this.sortBy = sortBy;
        this.sortByDirection = sortByDirection;
        this.searchTerm = searchTerm;
        this.favoritesOnly = favoritesOnly;
    }

    public HashSet<String> getTypes() {
        return types;
    }

    public HashSet<String> getGenres() {
        return genres;
    }

    public String getRatingString() {
        return ratingString;
    }

    public String getYearsString() {
        return yearsString;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSortByDirection() {
        return sortByDirection;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public boolean FavoritesOnly() {
        return favoritesOnly;
    }
}

