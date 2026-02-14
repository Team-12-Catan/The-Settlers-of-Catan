package src;
//public class Demonstrator {
//    static void main(String[] args){
//        Game gameOne = new Game(8192, 4);
//        gameOne.run();
//    }
//}

/**
 * Demonstrator class for the Game Simulator.
 * This class serves as the entry point to showcase the game logic,
 * including initial setup, resource production, and building mechanics.
 */
public class Demonstrator {

    public static void main(String[] args) {
        // --- 1. Initialization Phase ---
        // We create a simulation with a set number of rounds and players.
        // The Game constructor handles the creation of the Board, Bank, and Agents.
        int rounds = 10;
        int players = 3;

        System.out.println("DEBUG: Starting Simulation with " + players + " players.");
        Game simulation = new Game(rounds, players);

        /* * --- 2. Execution Phase ---
         * The run() method triggers the core game loop:
         * a) initialSetup(): Demonstrates player placement of settlements and roads.
         * b) turnLoop(): Iterates through rounds, handling:
         * - Dice rolling (MultiDice class)
         * - Resource production (produceResource method)
         * - Player actions (build method)
         * - Victory condition checks (10 Victory Points)
         */
        try {
            System.out.println("LOG: Entering Game Loop...");
            simulation.run();
        } catch (Exception e) {
            // Error handling to ensure the simulator provides feedback if logic fails
            System.out.println("CRITICAL: Simulation interrupted.");
            e.printStackTrace();
        }

        // --- 3. Termination Phase ---
        System.out.println("LOG: Simulation complete. Check console output for winner details.");
    }
}
