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
                "                                                           \n" +
                "8 8888     ,88'  8 8888 b.             8     ,o888888o.    \n" +
                "8 8888    ,88'   8 8888 888o.          8    8888     `88.  \n" +
                "8 8888   ,88'    8 8888 Y88888o.       8 ,8 8888       `8. \n" +
                "8 8888  ,88'     8 8888 .`Y888888o.    8 88 8888           \n" +
                "8 8888 ,88'      8 8888 8o. `Y888888o. 8 88 8888           \n" +
                "8 8888 88'       8 8888 8`Y8o. `Y88888o8 88 8888           \n" +
                "8 888888<        8 8888 8   `Y8o. `Y8888 88 8888   8888888 \n" +
                "8 8888 `Y8.      8 8888 8      `Y8o. `Y8 `8 8888       .8' \n" +
                "8 8888   `Y8.    8 8888 8         `Y8o.`    8888     ,88'  \n" +
                "8 8888     `Y8.  8 8888 8            `Yo     `8888888P'    ";

        System.out.println("Hello from\n" + logo);

        if (Storage.checkFile() && Storage.isEmptyFile()) {
            taskList.loadTasks();
            System.out.println(" \n\nHello! I'm King" + "\n" +
                               " What can I do for you?\n" + LINE);
        } else if (Storage.checkFile()) {
            try {
                taskList.loadTasks();
                System.out.println("\n\nWelcome back! You have tasks saved previously.");
                taskList.printList();
            } catch (KingException e) {
                System.out.println(e.getMessage() + LINE);
            }
        } else {
            Storage.readFile();
            System.out.println(" \n\nHello! I'm King" + "\n" +
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
