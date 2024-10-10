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
     * Closes the scanner and releases any resources associated with it.
     */
    public void close() {
        scanner.close();
    }

}
