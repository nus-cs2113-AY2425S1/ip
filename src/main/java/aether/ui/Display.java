package aether.ui;

/**
 * Handles the display logic for the task manager.
 * <p>
 * The {@code Display} class is responsible for presenting information to the user.
 * It manages the display of the start and end screens, separators, and responses to user commands.
 * This class centralizes all user interface outputs, ensuring consistent formatting and presentation.
 * </p>
 */
public class Display {

    /**
     * Displays the start screen with the application's logo and welcome message.
     * <p>
     * This method prints an ASCII art logo followed by a welcome message to greet the user
     * when the application starts. It also includes separators for better visual organization.
     * </p>
     */
    public static void showStartScreen() {
        String logo = "     ___   ______  _______  _    _  ______  _____\n"
                + "    /   | |  ____||__   __|| |  | ||  ____||  __ \\\n"
                + "   / /| | | |__      | |   | |__| || |__   | |__) |\n"
                + "  / /_| | |  __|     | |   |  __  ||  __|  |  _  /\n"
                + " /  __  | | |____    | |   | |  | || |____ | | \\ \\\n"
                + "/_/   |_| |______|   |_|   |_|  |_||______||_|  \\_\\";
        String startScreen = "Aether:\n" + "Hello! I'm Aether, your friendly assistant.\n"
                + "How can I help you today?";

        System.out.println(logo);
        printSeparator();
        System.out.println(startScreen);
        printSeparator();
    }

    /**
     * Displays the end screen with a goodbye message.
     * <p>
     * This method prints a farewell message to the user when the application is about to exit,
     * providing a polite and friendly closure to the session.
     * </p>
     */
    public static void showEndScreen() {
        String endScreen = "Aether:\n" + "Goodbye! Hope to see you again soon!";
        System.out.println(endScreen);
    }

    /**
     * Prints a separator line to the console.
     * <p>
     * This method outputs a line of underscores to visually separate different sections
     * of the user interface, enhancing readability and organization.
     * </p>
     */
    public static void printSeparator() {
        System.out.println("________________________________________________________________________");
    }

    /**
     * Displays a response message from Aether.
     * <p>
     * This method prints a message prefixed with "Aether:" to indicate that the response
     * is coming from the assistant. It is used for standard responses to user commands.
     * </p>
     *
     * @param message The message to be displayed to the user.
     */
    public static void response(String message) {
        System.out.println("Aether:\n" + message);
    }

    /**
     * Displays a response message without the "Aether:" prefix.
     * <p>
     * This method prints a message as-is, without any prefix. It is useful for listing
     * multiple items or providing detailed information where the prefix might be redundant.
     * </p>
     *
     * @param message The message to be displayed to the user.
     */
    public static void newLineResponse(String message) {
        System.out.println(message);
    }
}
