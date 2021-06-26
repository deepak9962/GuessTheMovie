import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessTheMovie {

    private static final Game game = new Game();

    public static void main(String[] args) {

        // Starting GuessTheMovie game;
        System.out.println("Hello there!\nWe have selected you a random movie name, Try to guess the movie;");
        System.out.println("Enter a letter to guess the movie: ");

        // Calling startGame function from Game.java
        game.startGame();
    }

    /**
     * Getting a random movie name from the file where many movies name are stored.
     * Scanner scans the file and split create an array of movies and select one random movie.
     *
     * @return a random selected movie
     */
    public static String searchMovie() {

        // ArrayList for string movie
        List<String> itemMovie = new ArrayList<>();

        // File to get the movie file list
        File file = new File("assets/MovieList");

        // Trying try/catch block in case of if the file is missing or some error with the file
        Scanner scanner = null;
        try {

            // attach the file to the scanner
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // String line to get each movie available in movie list file
        String str_line;

        // Looping until no more lines
        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            str_line = scanner.nextLine();
            str_line = str_line.trim();
            if (str_line.length() != 0) {

                // Creating a list of movies
                itemMovie.add(str_line);
            }
        }

        // getting the size of array
        int length  = itemMovie.size();

        // Returning a selected random movie
        return itemMovie.get(position(length));
    }

    /**
     * Position to get a random number to select a random movie
     *
     * @param length considered as size of array
     * @return a random number to get a random movie
     */
    private static int position(int length) {
        return (int) ((Math.random() * length) - 1);
    }
}
