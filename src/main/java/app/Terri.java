package app;

import exceptions.TerriException;
import taskmanager.TaskList;

import java.util.Scanner;

import static app.Messages.COMMAND_EXIT_TERRI;
import static app.Messages.HELP_MESSAGE;
import static app.Messages.OFFER_REFRESHER;
import static app.Messages.REFRESHER_ACCEPTED;
import static app.Messages.REFRESHER_DECLINED;
import static app.Messages.TERRI_EXIT_MSG;
import static app.Parser.parseInput;
import static app.UI.printDivider;
import static app.UI.printInstructions;
import static app.UI.printWelcomeMessage;

/**
 * The {@code Terri} class is the main entry point of the chatbot application.
 * It processes user inputs, handles task management commands, and provides
 * instructions or reminders when needed. The chatbot continues to run in a loop
 * until the user terminates it with an exit command.
 *
 * <p>The class offers functionality for adding tasks, marking tasks as complete or incomplete,
 * listing tasks, and handling events or deadlines. The chatbot also provides error handling
 * and user guidance when unrecognized commands are entered.</p>
 */

public class Terri {

    /**
     * The main method initializes the chatbot and starts processing user input.
     * It continually reads user input and processes task-related commands, until
     * the user types "bye" to exit the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        printWelcomeMessage();

        // Initialise tasks from save file (if present)
        TaskList.loadTaskData();

        // Initialise a scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Continually check for and process user input
        while (true) {
            String userInput = scanner.nextLine().trim();

            // Short-circuit check for exit input.
            if (userInput.equalsIgnoreCase(COMMAND_EXIT_TERRI)) {
                // Exit Chatbot and close scanner.
                exitTerri(scanner);
                break;
            }

            // Process input and catch exceptions appropriately
            try {
                parseInput(userInput);
            }
            catch (TerriException ex) {
                // Print custom error message and offer general refresher
                System.out.println(ex.getMessage());
                System.out.println(OFFER_REFRESHER);

                if (scanner.nextLine().equalsIgnoreCase("Y")) {
                    printDivider();
                    System.out.println(REFRESHER_ACCEPTED);
                    printInstructions();
                } else {
                    System.out.println(REFRESHER_DECLINED);
                }
            }
            printDivider(); 
        }
    }

    /**
     * Exits the chatbot by printing a goodbye message, closing the scanner,
     * and terminating the program.
     *
     * @param scanner The {@code Scanner} object used to read user input.
     */
    public static void exitTerri(Scanner scanner) {
    System.out.println(TERRI_EXIT_MSG);
    printDivider();
    // Close scanner after loop ends and program terminates
    scanner.close();
    }




}
