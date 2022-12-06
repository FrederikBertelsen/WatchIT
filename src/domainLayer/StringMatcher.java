package domainLayer;

import java.util.ArrayList;

public class StringMatcher {
    public static String searchTerm = "";

    public static String[] movieNames = new String[]{"The Godfather", "The Shawshank Redemption", "Schindler's List", "Raging Bull", "Casablanca", "Citizen Kane", "Gone With The Wind", "The Wizard Of Oz", "One Flew Over The Cuckoo's Nest", "Lawrence Of Arabia", "Vertigo", "Psycho", "The Godfather part II", "On The Waterfront", "Sunset Boulevard", "Forrest Gump", "The Sound Of Music", "12 Angry Men", "West Side Story", "Star Wars", "2001 A Space Odyssey", "ET", "The Silence Of The Lambs", "Chinatown", "The Bridge Over The River Kwai", "Singin' In The Rain", "It's A Wonderful Life", "Dr. Strangelove Or How I Learned To Stop Worrying And Love The Bomb", "Some Like It Hot", "Ben Hur", "Apocalypse Now", "Amadeus", "Lord Of The Rings - The Return Of The King", "Gladiator", "Titanic", "From Here To Eternity", "Saving Private Ryan", "Unforgiven", "Raiders Of The Lost Ark", "Rocky", "A Streetcar Named Desire", "A Philadelphia Story", "To Kill A Mockingbird", "An American In Paris", "The Best Years Of Our Lives", "My Fair Lady", "A Clockwork Orange", "Doctor Zhivago", "The Searchers", "Jaws", "Patton", "Butch Cassidy And The Sundance Kid", "The Treasure Of The Sierra Madre", "The Good, The Bad And The Ugly", "The Apartment", "Platoon", "High Noon", "Braveheart", "Dances With Wolves", "Jurassic Park", "The Exorcist", "The Pianist", "Goodfellas", "The Deer Hunter", "All Quiet On The Western Front", "Bonny And Clyde", "The French Connection", "City Lights", "It Happened One Night", "A Place In The Sun", "Midnight Cowboy", "Mr Smith Goes To Washington", "Rain Man", "Annie Hall", "Fargo", "Giant", "Shane", "Grapes Of Wrath", "The Green Mile", "Close Encounters", "Nashville", "Network", "The Graduate", "American Graffiti", "Pulp Fiction", "Terms of Endearment", "Good Will Hunting", "The African Queen", "Stagecoach", "Mutiny On The Bounty", "The Great Dictator", "Double Indemnity", "The Maltese Falcon", "Wuthering Heights", "Taxi Driver", "Rear Window", "The Third Man", "Rebel Without A Cause", "North By Northwest", "Yankee Doodle Dandy"};

    public static void main(String[] args){
        searchTerm = searchTerm.toLowerCase();

        for (int i = 0; i < movieNames.length; i++) {
            movieNames[i] = movieNames[i].toLowerCase();
        }

        System.out.println("SEARCH TERM: " + searchTerm);
        ArrayList<String> matchedNamed = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            matchedNamed = new ArrayList<>();

            for (String movieName: movieNames){
                int score = calcStringMatch(searchTerm, movieName);

                if (score > searchTerm.length()/2){
                    matchedNamed.add(movieName);
//                    System.out.println(movieName +": " + score);
                }
            }
        }
        for (String str : matchedNamed){
            System.out.println(str);
        }



    }


    public static int calcStringMatch(String stringA, String stringB) {
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

        int highScore = 0;
        for (int i = 0; i < extraLength; i++) {
            int newScore = calcStringLikeness(longString.substring(i), shortString);
            if (newScore > highScore) highScore = newScore;
        }
        return highScore;
    }

    public static int calcStringLikeness(String stringA, String stringB) {
        int score = 0;

        if (stringA.charAt(0) == stringB.charAt(0)){
            score += 2;

            if (stringA.length() == 1 || stringB.length() == 1)return score;

            score += calcStringLikeness(stringA.substring(1),stringB.substring(1));
        } else if (stringB.length() > 1 && stringA.charAt(0) == stringB.charAt(1)){
            score += 0;
            score += calcStringLikeness(stringA,stringB.substring(1));
        } else if (stringA.length() > 1 && stringA.charAt(1) == stringB.charAt(0)){
            score += 0;
            score += calcStringLikeness(stringA.substring(1),stringB);
        } else if (stringA.length() == 1 || stringB.length() == 1){
            return score;
        }
        else {
            score -= 3;
            score += calcStringLikeness(stringA.substring(1),stringB.substring(1));
        }


        return score;
    }
}
