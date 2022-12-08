package domainLayer;

import domainLayer.dataStructure.Media;
import domainLayer.dataStructure.Movie;

import java.util.*;

public class StringMatcher {

    //    public static void main(String[] args) {
//        ArrayList<Media> medias = new ArrayList<>();
//        medias.add(new Movie("batman", 1990, new String[]{"Horror"}, 10, null));
//
//        String searchTerm = "batman";
//
////        int score = calcStringLikeness(searchTerm, medias.get(0).getTitle());
////        System.out.println(score);
//
//        getMatches(searchTerm,medias);
//    }
    private static double getMinimumScore(String searchTerm) {
        return 1.0 * searchTerm.length() / 1.2;
    }

    public static ArrayList<Media> getMatches(String searchTerm, ArrayList<Media> medias) {
        searchTerm = searchTerm.toLowerCase();

        double minimumScore = getMinimumScore(searchTerm);
        System.out.println("Minimum Score: " + minimumScore);

        ArrayList<Map.Entry<Media, Integer>> matchedMedias = new ArrayList<>();
        for (Media media : medias) {
            String mediaName = media.getTitle().toLowerCase();
            int score = calcStringMatch(searchTerm, mediaName);

            if (score >= minimumScore) {
                matchedMedias.add(new AbstractMap.SimpleEntry<>(media, score));
            }
        }

        matchedMedias.sort(Map.Entry.comparingByValue());

        ArrayList<Media> output = new ArrayList<>();
        for (Map.Entry<Media, Integer> entry : matchedMedias) {

            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
            output.add(entry.getKey());
        }
        System.out.println();

        return output;
    }

    private static int calcStringMatch(String stringA, String stringB) {
        String longString;
        String shortString;
        if (stringA.length() > stringB.length()) {
            longString = stringA;
            shortString = stringB;
        } else {
            longString = stringB;
            shortString = stringA;
        }
        int extraLength = longString.length() - shortString.length();

        if (longString.contains(shortString)) {
            return shortString.length() * 3;
        }
        // if there is no extra length,
        if (extraLength == 0) {
            return calcStringLikeness(longString, shortString);
        }

        int highScore = 0;
        for (int i = 0; i < extraLength; i++) {
            int newScore = calcStringLikeness(longString.substring(i), shortString);
            if (newScore > highScore) highScore = newScore;
        }
        return highScore;
    }

    private static int calcStringLikeness(String stringA, String stringB) {
        int score = 0;

        if (stringA.charAt(0) == stringB.charAt(0)) {
            score += 2;

            if (stringA.length() == 1 || stringB.length() == 1) return score;

            score += calcStringLikeness(stringA.substring(1), stringB.substring(1));
        } else if (stringB.length() > 1 && stringA.charAt(0) == stringB.charAt(1)) {
            score += 0;
            score += calcStringLikeness(stringA, stringB.substring(1));
        } else if (stringA.length() > 1 && stringA.charAt(1) == stringB.charAt(0)) {
            score += 0;
            score += calcStringLikeness(stringA.substring(1), stringB);
        } else if (stringA.length() == 1 || stringB.length() == 1) {
            return score;
        } else {
            score -= 3;
            score += calcStringLikeness(stringA.substring(1), stringB.substring(1));
        }

        return score;
    }
}
