package hsien.ui;

import java.util.List;
import java.util.Scanner;

/**
 * User Interface class for the Hsien application.
 * This class handles all interactions with the user,
 * including displaying messages, reading commands, and showing task lists.
 */
public class Ui {
    private final Scanner in;

    /**
     * Constructor for the Ui class. Initializes the Scanner object for user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the logo of the Hsien application to the console.
     */
    public void printLogo() {
        System.out.println(" _   _         _____        _ _   _ ");
        System.out.println("| | | | ##### |_   _|##### |   \\ | |");
        System.out.println("| |_| |#        | |  #     | |\\ \\| |");
        System.out.println("|  _  | #####   | |  ##### | | \\ \\ |");
        System.out.println("| | | |      # _| |_ #     | |  \\  |");
        System.out.println("|_| |_| ##### |_____|##### |_|   \\_|\n");
    }

    /**
     * Prints a horizontal line to separate sections of output.
     */
    public void printLine() {
        System.out.println("\n" + "-".repeat(50) + "\n");
    }

    /**
     * Displays a welcome message when the application starts.
     * Includes the logo and a greeting.
     */
    public void welcomeMessage() {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot... Please use me as a task planner");
        printLine();
    }

    /**
     * Prints the list of valid commands to the console.
     *
     * @param validCommands the list of valid commands.
     */
    public void printCommands(List<String> validCommands) {
        System.out.println("These are the possible commands:");
        for (int i=1; i<= validCommands.size(); i+=1) {
            System.out.printf("%d. %s\n", i, validCommands.get(i - 1));
        }
        System.out.print("Please enter a command/add task (type 'bye' to exit): ");
    }

    /**
     * Reads a command and description entered by the user.
     *
     * @return the command and description entered by the user.
     */
    public String readInput() {
        return in.nextLine();
    }

    /**
     * Closes the Scanner object to free resources.
     */
    public void close() {
        in.close();
    }
}
