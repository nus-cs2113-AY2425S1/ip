package nova;

import java.util.Scanner;

/**
 * The main entry point for the Nova task management program.
 * This class initializes the user interface, task list, and command parser.
 * It continuously processes user input until the "bye" command is issued.
 */
public class Nova {

    /**
     * The main method where the program starts.
     * It displays the welcome message, initializes the task manager and parser,
     * loads the task storage, and enters a loop to handle user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui.displayWelcomeMessage();
        TaskList taskManager = new TaskList();
        Parser parser = new Parser(taskManager);
        Storage.createStorage();
        Scanner sc = new Scanner(System.in);

        // Continuously reads and processes user input
        while (true) {
            String[] userInput = getUserInput(sc);
            if (parser.handleInput(userInput)) {
                return; // Exit the program if the "bye" command is issued
            }
        }
    }

    /**
     * Retrieves user input from the scanner and splits it into command and arguments.
     * If no input is provided, it defaults to the "bye" command.
     *
     * @param sc The {@code Scanner} object for reading user input.
     * @return An array containing the command and its arguments.
     */
    private static String[] getUserInput(Scanner sc) {
        if (!sc.hasNextLine()) {
            return new String[]{"bye"}; // Default command to exit the program
        }
        String info = sc.nextLine();
        return info.split(" ", 2); // Split input into command and arguments
    }
}
