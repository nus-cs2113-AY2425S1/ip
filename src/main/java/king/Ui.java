package king;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles user interaction for King.
 */
public class Ui {

    private final static String LINE = "____________________________________________________________\n";

    /**
     * Prompts the user for input and returns the trimmed input.
     *
     * @return The trimmed input.
     */
    protected static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            System.out.print("Command cannot be empty. Please try again.\n" + LINE);
            return getUserInput();
        } else {
            return input.trim();
        }
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    protected static void showError(String message) {
        System.out.println(LINE + message + LINE);
    }

    /**
     * Greets the user and loads tasks.
     * If tasks exist in storage, they are loaded and displayed to the user.
     * Otherwise, the program starts with no tasks.
     *
     * @param taskList The task list to load tasks into, if applicable.
     * @throws KingException If there is an issue with loading tasks.
     * @throws IOException   If an I/O error occurs while accessing the storage file.
     */
    protected static void toGreet(TaskList taskList) throws KingException, IOException {
        String logo =
                "| |/ /|_ _|| \\| | / _` |\n" +
                "| ' <  | | | .` || (_| |\n" +
                "|_|\\_\\|___||_|\\_| \\__,_|\n";

        System.out.println("\nHello from\n" + logo);

        if (Storage.checkFile()) {
            try {
                taskList.loadTasks();
                System.out.println("Welcome back! You have tasks saved previously.");
                taskList.printList();
            } catch (KingException e) {
                System.out.println(e.getMessage() + LINE);
            }
        } else {
            Storage.readFile();
            System.out.println(" Hello! I'm King" + "\n" +
                               " What can I do for you?\n" + LINE);
        }
    }

    /**
     * Displays an exit message.
     */
    public static void toExit() {
        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
    }
}
