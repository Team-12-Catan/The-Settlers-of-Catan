package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demonstrator {

    public static void main(String[] args) {
        // Game configuration
        //int rounds = 10;
        int maxRounds;
        int numPlayers = 4;

        // Create the game
        Game game;
        System.out.println("↳ Starting Simulation\n");
        // Read user inputs from a file instead of typing
        try {
            Scanner fileScanner = new Scanner(new File("demo_input.txt"));

            //Read the first line to get the max rounds
            String line = fileScanner.nextLine().trim();

            //Split at ':' to get the number
            String[] parts = line.split(":");
            maxRounds = (Integer.parseInt(parts[1].trim()))/numPlayers;
            game = new Game(maxRounds, numPlayers);

            //pass this file scanner to the game run method
            game.run(fileScanner);

            fileScanner.close(); //prevents leaks
        }
        catch (FileNotFoundException e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("↳ Simulation Completed");
    }
}
