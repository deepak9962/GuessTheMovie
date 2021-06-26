import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Game {

    private final String movie = GuessTheMovie.searchMovie();

    // String array for daces
    private final String[] daces = new String[movie.length()];

    // An empty dace
    private final String emptyDace = "_";

    // Scanner to get the text from console
    private final Scanner scanner = new Scanner(System.in);

    // check whether the user won or loose, Default: false
    private boolean hasWon = false;

    /**
     * This is where the actual game process starts and this function gets the letters
     * from users and replaces with the daces to reveal a movie letter.
     * <p>
     * if the letter is right it according to the movie it selected randomly it will
     * reveal that movie's letter position and repeats the same process using loops.
     * <p>
     * if the letter is wrong then user looses he guess(es) points and end up loosing
     * if wrong letters given until the guess(es) left.
     * <p>
     * Total guess(es) 10
     * <p>
     * If user gets the right letter, guess(es) points will not decrease but if user
     * gets the wrong letter, guess(es) points will decrease by 1.
     * <p>
     * If user guess the write movie, he wins.
     * If user is not able to guess, he looses.
     */
    public void startGame() {

        // creates loop for empty daces to get the exact quantity of daces as per movie has letters
        for (int i = 0; i < movie.length(); i++) {

            // Add the spaces if movie has any
            for (int j = 0; j < movie.length(); j++) {
                if (j == movie.indexOf(" ", j)) {
                    daces[j] = " ";
                }
            }

            daces[i] = emptyDace;
        }

        // prints out the daces on screen
        System.out.println(createString());

        // total guess(es) point
        int count = 10;

        // loop to get the letter from user and check whether its right or not,
        // and if the letter is right then replace the letter with dace on it's position
        // or if the letter is wrong then decreases the guess(es) points.
        for (int i = 0; i <= movie.length(); i++) {

            // prints how many guess(es) you have or left
            System.out.println("You have " + count + " guess(es)");

            // Get's the letters from user
            String guess = scanner.next();

            // check if the user entered multiple characters.
            if (guess.length() == 1) {

                // check the letter whether it is the right or wrong
                // and if the letter is right then replace the letter with dace on it's position.
                for (int j = 0; j < movie.length(); j++) {

                    // searches and compares each letter one by one of the movie and finds the index of
                    // the letter if the user provided letter matches, replace the letter.
                    //
                    // since we have exact number of loop as equal to movie has letters.
                    // it checks whether the letter is guessed already and if it is guessed
                    // already then decrease the loop by one, so that the loop keeps continues.
                    if (daces[j].contains(guess)) {
                        System.out.println("You already guessed it! Please try different letter!");
                        i--;
                    } else if (j == movie.indexOf(guess) && daces[j].contains(emptyDace)) {
                        daces[j] = guess;

                        // checks if the movie is guessed or if there is no empty space left.
                        checkWinner();

                        // increase the guess(es) by one if guess is write because below down it
                        // is going to decrease, so we don't want users to loose points for guessing it right.
                        count++;
                    } else {
                        if (j == movie.indexOf(guess, j) && daces[j].contains(emptyDace)) {
                            daces[j] = guess;
                            checkWinner();
                        }
                    }
                }

                // Print the updated string
                System.out.println(createString());

                // if the guess is wrong this statement prints and
                // decrease the loop by one, so that the loop keeps continues.
                if (!(movie.contains(guess))) {
                    System.out.println("Good guess, Try again!");
                    i--;
                }

                // If guessed wrong then guess(es) point will decrease
                count--;

                // This method checks if the user has won, prints the won statement
                // or if the user looses then loose statement prints.
                if (hasWon) {
                    System.out.println("WOW CONGRATULATIONS YOU WON!" +
                            "\n" +
                            "You guessed the right movie!");
                    System.out.println("The movie was : " + movie);
                    restart();
                    return;
                } else if (count == 0) {
                    System.out.println("You used all your guess(es) and not get the movie\n" +
                            "YOU LOOSE!");
                    System.out.println("The movie was : " + movie);
                    restart();
                    return;
                }
            } else {
                System.out.println("Please enter one letter at a time.");
                i--;
            }
        }
    }

    private void restart() {
        System.out.println("1. Play Again\n2. Exit");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1) {
            new Game().startGame();
        } else {
            System.out.println("Thank you for playing, see you soon!");
        }
    }

    /**
     * StringBuilder creates and simple looking dace string hiding the movie
     * name and having the same length as movie has.
     *
     * @return a simple looking string instead of an array with commasZ
     */
    private @NotNull
    StringBuilder createString() {
        StringBuilder stringBuilder = new StringBuilder();

        // looping through the length of movie and appending the daces
        // and forming a one simple movie name, without commas
        for (int i = 0; i < movie.length(); i++) {
            stringBuilder.append(daces[i]);
        }
        return stringBuilder;
    }

    /**
     * This function checks whether the user won or not.
     */
    private void checkWinner() {

        // Getting the length of movie
        int count = movie.length();

        // Creating a loop to check if the user guessed the movie or
        // it still empty daces has left.
        // If there is still empty daces left then don't decrease the count
        // of this method (which is movie length) or if there is no
        // empty daces left then set the count to 0 to declare winner
        for (int i = 0; i < movie.length(); i++) {
            if (!(daces[i].contains(emptyDace))) {
                count--;
            }
        }

        // if there is no emptyDaces which means user won then variable count becomes 0 and
        // sets the boolean variable hasWon to true
        if (count == 0) {
            hasWon = true;
        }
    }
}
