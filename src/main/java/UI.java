import java.util.Scanner; // Import Scanner for user input

/**
 * The UI class handles interaction with the user by reading input and displaying output messages.
 */
public class UI {

    // Constant for common error message to improve code readability and reusability
    private final static String PORFAVOR = "Por Favor?\n";
    private final static String SEPARATOR = "____________________________________________________________\n";
    private final static String BYEMESSAGE = "Adios amigo, la familia will miss you\n";
    private final static String GREETINGMESSAGE =
            "               ._-'-_ .\n" +
                    "          . '  /_-_-_\\   ` .\n" +
                    "       .'     |-_-_-_-|      `.\n" +
                    "      (       `.-_-_-.'        )\n" +
                    "      !`.                    .'!\n" +
                    "        ! ` .            . ' !\n" +
                    "          ! ! ! ! ! ! ! !  !\n" +
                    "            / /       \\ \\\n" +
                    "          _-| \\___ ___/ /-_\n" +
                    "         (_ )__\\_)\\(_/__( _)\n" +
                    "             ))))\\X\\ ((((\n" +
                    "               \\/ \\/ \n" +
                    "Hola Amigo, I am Juan Cervantes Salamanca from Michoacan \n" +
                    "Welcome to la familia \n" +
                    "How can we help you? \n";

    private Scanner scanner;

    /**
     * Constructor for UI.
     * Initializes the scanner for reading user input.
     */
    public UI() {
        scanner = new Scanner(System.in); // Initialize the scanner for reading user input
    }

    /**
     * Reads the user's input from the console.
     *
     * @return The user's input as a String.
     */
    public String readUserInput() {
        return scanner.nextLine(); // Read user input
    }

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a separator line to the console for clean output formatting.
     */
    public void lineMessage() {
        System.out.print(SEPARATOR);
    }

    /**
     * Prints a message with a "Por Favor" prefix to the console.
     *
     * @param message The message to be printed.
     */
    public void porFavorMessage(String message) {
        System.out.println(PORFAVOR + message);
    }

    /**
     * Displays a welcome message when the program starts.
     */
    public void helloMessage() {
        System.out.print(SEPARATOR);
        System.out.print(GREETINGMESSAGE); // Print the welcome message
        System.out.print(SEPARATOR);
    }

    /**
     * Displays a goodbye message when the program ends.
     */
    public void byeMessage() {
        System.out.print(BYEMESSAGE); // Print the goodbye message
        System.out.print(SEPARATOR);
    }
}
