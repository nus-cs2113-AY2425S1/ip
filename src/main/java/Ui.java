import java.util.Scanner;

/**
 * Handles user input and output. Manages interaction between the user and the Bebe application.
 * Responsible for reading user commands and displaying output messages.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLogo();
        showLine();
        System.out.println("Hello! I'm Bebe, your task manager.");
        System.out.println("What can I do for you today?");
    }

    /**
     * Reads and returns the user's input command.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line to separate sections of the output.
     */
    public void showLine() {
        System.out.println("_______________________________________________________");
    }

    /**
     * Displays an error message for when tasks fail to load from storage.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to display.
     */
    public void show(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of available commands to the user.
     */
    public void showHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("  todo <task description>          - Adds a ToDo task.");
        System.out.println("  deadline <task description> /by <date/time>  - Adds a Deadline task.");
        System.out.println("  event <task description> /from <start time> /to <end time>  - Adds an Event task.");
        System.out.println("  list                            - Lists all tasks.");
        System.out.println("  mark <task number>              - Marks a task as done.");
        System.out.println("  unmark <task number>            - Marks a task as not done.");
        System.out.println("  delete <task number>            - Deletes a task.");
        System.out.println("  find                            - Shows all task that matches ur description.");
        System.out.println("  help                            - Shows this help message.");
        System.out.println("  bye                             - Exits the chatbot.");
    }

    /**
     * Displays the application logo to the user.
     */
    public void showLogo() {
        String logo = """
                 ____    ______   ____    ______ \s
                 |  _ \\  |  ____| |  _ \\  |  ____|\s
                 | |_) | | |__    | |_) | | |__   \s
                 |  _ <  |  __|   |  _ <  |  __|  \s
                 | |_) | | |____  | |_) | | |____ \s
                 |____/  |______| |____/  |______|\s
                """;
        System.out.println(logo);
    }
}
;