package bron.ui;

import java.util.Scanner;

/**
 * Handles the user interface for the chatbot.
 * Provides methods to display messages and read user commands.
 */
public class TextUI {

    private static final String LOGO = """
              ____    ____      ____    _     _
             |  _ \\  |   _\\   /  __  \\ | \\   | |
             | |_) | | |_) |  | |  | | |  \\  | |\s
             |  _ <  | ___/   | |  | | | |\\\\ | |
             | |_) | | | \\ \\  | |__| | | | \\\\| |
             |____/  |_|  \\_\\  \\____/  |_|  \\__|
            """;

    private final Scanner scanner;

    /**
     * Constructs a new TextUI object and initializes the scanner for reading input.
     */
    public TextUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the introductory message when the chatbot starts.
     * Includes the logo and greeting message.
     */
    public static void displayIntro() {
        System.out.println(LOGO + "Hello! I'm Bron\n" + "What can I do for you?\n");
    }

    /**
     * Prompts the user to enter a command and reads the command input from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    /**
     * Displays the goodbye message when the chatbot ends.
     */
    public static void showByeMessage() { System.out.println("Catch you on the flip cuh"); }

    /**
     * Displays a help message outlining all available commands and their usage.
     * This method provides detailed explanations and examples for each command that the chatbot can execute.
     */
    public static void showHelp() {
        System.out.println("Here are the available commands and their usage:");
        System.out.println("1. todo [task description] - Adds a to-do task to your list.");
        System.out.println("   Example: todo read book");
        System.out.println("2. deadline [task description] /by [yyyy-MM-dd HHmm] - Adds a deadline task with a specific date and time.");
        System.out.println("   Example: deadline return book /by 2023-10-12 1800");
        System.out.println("3. event [task description] /from [start time] /to [end time] - Adds an event task with start and end times.");
        System.out.println("   Example: event project meeting /from 1400 /to 1600");
        System.out.println("4. list - Lists all tasks in your task list.");
        System.out.println("5. mark [task number] - Marks the specified task as done.");
        System.out.println("6. unmark [task number] - Marks the specified task as not done.");
        System.out.println("7. delete [task number] - Deletes the specified task from the list.");
        System.out.println("8. find [keyword] - Finds all tasks containing the specified keyword.");
        System.out.println("9. help - Displays this help message.");
        System.out.println("10. bye - Exits the application.");
    }

    /**
     * Closes the scanner and releases any resources associated with it.
     */
    public void close() {
        scanner.close();
    }

}
