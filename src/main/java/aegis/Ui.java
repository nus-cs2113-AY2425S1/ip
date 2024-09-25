package aegis;

import java.util.Scanner;
import java.util.ArrayList;

import aegis.task.Task;

/**
 * The Ui class handles all user interactions in the Aegis application.
 * It is responsible for displaying messages, reading user input, and showing errors and task lists.
 */
public class Ui {
    private final Scanner scanner;
    private final static String AEGIS_LOGO = """
                                                                            \s
                        **                                                  \s
                     *****                                  *               \s
                    *  ***                                 ***              \s
                       ***                                  *               \s
                      *  **                                          ****   \s
                      *  **          ***        ****      ***       * **** *\s
                     *    **        * ***      *  ***  *   ***     **  **** \s
                     *    **       *   ***    *    ****     **    ****      \s
                    *      **     **    ***  **     **      **      ***     \s
                    *********     ********   **     **      **        ***   \s
                   *        **    *******    **     **      **          *** \s
                   *        **    **         **     **      **     ****  ** \s
                  *****      **   ****    *  **     **      **    * **** *  \s
                 *   ****    ** *  *******    ********      *** *    ****   \s
                *     **      **    *****       *** ***      ***            \s
                *                                    ***                    \s
                 **                            ****   ***                   \s
                                             *******  **                    \s
                                            *     ****                      \s
                                                                            \s""";

    /**
     * Constructs a Ui object that initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message, including the Aegis logo, and prompts the user for input.
     */
    public void showWelcomeMessage() {
        System.out.println(AEGIS_LOGO);
        System.out.println("Hello! This is Aegis, an Anti-Shadow Suppression Weapon and a chatbot.");
        System.out.println("What can I do for you?");
        System.out.println();
        showLine();
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return A trimmed string of the user input command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a line separator to visually divide sections in the console output.
     */
    public void showLine() {
        System.out.println("^_^ -------------------------------------------------- ^_^");
    }

    /**
     * Displays an error message when the task list fails to load.
     */
    public void showLoadingError() {
        System.out.println(" No previous task list found. Starting fresh.");
    }

    /**
     * Displays an error message with the specified details.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println();
        System.out.printf(" Anomaly detected: %s%n", message);
        System.out.println();
    }

    /**
     * Displays a list of tasks that match the user's search criteria.
     *
     * @param matchingTasks An ArrayList of tasks that match the search keyword.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println();
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + matchingTasks.get(i));
        }
        System.out.println();
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
