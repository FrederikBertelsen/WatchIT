package domainLayer;

import domainLayer.dataStructure.Media;

import java.util.*;

public class StringMatcher {

    // Beregner minimumsscoren for en given søgeterm
    private static double calcMinimumScore(String searchTerm) {
        return 1.0 * searchTerm.length() / 1.2;
    }


    // Finder matchende medier for en given søgeterm
    public static ArrayList<Media> getMatches(String searchTerm, ArrayList<Media> mediaList) {
        // Konverterer søgetermen til lower case
        searchTerm = searchTerm.toLowerCase();

        // Beregner minimumsscoren
        double minimumScore = calcMinimumScore(searchTerm);

        // Liste til matchende medier
        ArrayList<MediaScore> matchedMediaList = new ArrayList<>();

        // Gennemgår alle medier og finder matchende medier
        for (Media media : mediaList) {
            String mediaName = media.getTitle().toLowerCase();

            // Hvis medie titlen indeholder søge termet, så er der et perfect match. Og scoren bliver sat til den højeste mulige match-score (Integer.MAX_VALUE).
            int score;
            if (mediaName.contains(searchTerm)) {
                score = Integer.MAX_VALUE;
            } else {
                score = calcStringMatch(searchTerm, mediaName);
            }

            // Tilføjer mediet til listen, hvis scoren er større eller lig med minimumsscoren
            if (score >= minimumScore) {
                System.out.println(minimumScore);
                System.out.println(mediaName);
                System.out.println(score);
                matchedMediaList.add(new MediaScore(media, score));
            }
        }

        // Sorterer listen af matchende medier efter score
        matchedMediaList.sort(Comparator.comparing(MediaScore::getScore));

        // Liste til output
        ArrayList<Media> output = new ArrayList<>();

        // Gennemgår alle matchende medier og tilføjer dem til output-listen
        for (MediaScore mediaScore : matchedMediaList) {
            output.add(mediaScore.getMedia());
        }

        // Returnerer listen af matchende medier
        return output;
    }

    // Denne metode beregner en match-score for to givet strenge.
    // Match-scoret er et tal, der repræsenterer, hvor godt de to strenge passer sammen.
    // Jo højere match-score, jo bedre passer strenge sammen.
    private static int calcStringMatch(String stringA, String stringB) {
        // Lav longString og shortString variabler for at holde styr på,
        // hvilken af de to strenge, der er den længste og den korteste.
        String longString;
        String shortString;
        if (stringA.length() > stringB.length()) {
            longString = stringA;
            shortString = stringB;
        } else {
            longString = stringB;
            shortString = stringA;
        }
        // Find forskellen i længde mellem de to strenge.
        int lengthDiff = longString.length() - shortString.length();
        // Hvis længden på de to strenge er den samme, så beregn match-scoren ved hjælp af calcStringLikeness metoden.
        if (lengthDiff == 0) {
            return calcStringLikeness(longString, shortString);
        }
        // For hver mulig sub-streng af den lange streng, beregn match-scoren ved hjælp af calcStringLikeness metoden.
        // Hvis den nye score er højere end den nuværende højesteScore, så opdater højesteScore til den nye score.
        int highestScore = 0;
        for (int i = 0; i < lengthDiff; i++) {
            int newScore = calcStringLikeness(longString.substring(i), shortString);
            if (newScore > highestScore) highestScore = newScore;
        }
        // Returner den højesteScore, som er den bedste match-score for de to strenge.
        return highestScore;
    }

    // metoden calcStringLikeness() tager to strenge (longString og shortString) som input,
// og returnerer en score, der afspejler hvor tæt de to strenge matcher hinanden.
    private static int calcStringLikeness(String longString, String shortString) {
// scoren starter på 0
        int score = 0;
        // Hvis de første bogstaver i de to strenge er ens, så øges scoren med 2, da der er et bogstavs-match
        if (longString.charAt(0) == shortString.charAt(0)) {
            score += 2;

            // Hvis enten longString eller shortString kun har et bogstav, så returneres scoren
            if (longString.length() == 1 || shortString.length() == 1) return score;

            // Ellers, beregnes scoren for de resterende dele af de to strenge ved at kalde
            // calcStringLikeness() rekursivt.
            score += calcStringLikeness(longString.substring(1), shortString.substring(1));
        }
        // hvis det første bogstav i longString er det samme som det andet bogstav i shortString,
        // så kalder calcStringLikeness() rekursivt med de 2 stregne, uden det første bogstav i shortString.
        else if (shortString.length() > 1 && longString.charAt(0) == shortString.charAt(1)) {
            score += calcStringLikeness(longString, shortString.substring(1));
        }
        // hvis det første bogstav i shortString er det samme som det andet bogstav i longString,
        // så kalder calcStringLikeness() rekursivt med de 2 stregne, uden det første bogstav i longString.
        else if (longString.length() > 1 && longString.charAt(1) == shortString.charAt(0)) {
            score += calcStringLikeness(longString.substring(1), shortString);
        }
        // Hvis en af strengende er 1 bogstav lang, returneres scoren, da vi ikke kan finde flere bogstavs-matches
        else if (longString.length() == 1 || shortString.length() == 1) {
            return score;
        }
        // Hvis der ikke er noget bogstavs-match, falder scoren med 3,
        // og calcStringLikeness() kalder sig selv uden det første bogstav i hver streng.
        else {
            score -= 3;
            score += calcStringLikeness(longString.substring(1), shortString.substring(1));
        }

        return score;
    }
}
