import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UI component of PlopBot that handles all user interactions.
 * This class is responsible for displaying messages to the user and
 * getting user input from the command line interface.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "//" + "\u2500".repeat(50);
    private static final String ECHO_LINE = "    " + "\u2500".repeat(48);
    private Scanner scanner;

    /**
     * Constructs a new UI instance and initializes the Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when PlopBot starts.
     * Includes a greeting and prompt for user input.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm PlopBot.");
        System.out.println("What can I do for you today?\n");
    }

    /**
     * Displays the exit message when PlopBot terminates.
     * Shows a farewell message to the user.
     */
    public void showExit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Thank you for choosing PlopBot. Have a great day!\n");
    }

    /**
     * Reads a command from the user's input.
     *
     * @return - A String containing the user's command, with leading and trailing whitespace removed
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays all tasks in the user's task list.
     * Each task is numbered and shown with its complete details.
     *
     * @param tasks - An ArrayList of Task objects to be displayed
     */
    public void showTasks(ArrayList<Task> tasks) {
        System.out.println(ECHO_LINE);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(ECHO_LINE);
    }

    /**
     * Confirms the addition of a new task and shows the updated task count.
     *
     * @param task       - The Task that was added to the list
     * @param totalTasks - The current total number of tasks in the list
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(ECHO_LINE);
        System.out.println("    Added: " + task);
        System.out.println("    You now have " + totalTasks + " tasks in the list.");
        System.out.println(ECHO_LINE);
    }

    /**
     * Confirms the removal of a task and shows the updated task count.
     *
     * @param task       - The Task that was removed from the list
     * @param totalTasks - The current total number of tasks in the list
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println(ECHO_LINE);
        System.out.println("    Removed: " + task);
        System.out.println("    You now have " + totalTasks + " tasks in the list.");
        System.out.println(ECHO_LINE);
    }

    /**
     * Displays tasks that match a search criterion.
     * If no tasks match, displays an appropriate message.
     *
     * @param tasks - An ArrayList of Task objects that match the search criteria
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(ECHO_LINE);
        if (tasks.isEmpty()) {
            System.out.println("    No matching tasks found.");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(ECHO_LINE);
    }

    /**
     * Displays confirmation that a task has been marked as complete.
     *
     * @param task - The Task that was marked as complete
     */
    public void showTaskMarked(Task task) {
        System.out.println("    I've marked this task as done:");
        System.out.println("    " + task.toString());
    }

    /**
     * Displays confirmation that a task has been unmarked (set as incomplete).
     *
     * @param task - The Task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("    I've unmarked this task:");
        System.out.println("    " + task.toString());
    }

    /**
     * Displays an error message to the user.
     * The message is formatted with proper indentation and line breaks.
     *
     * @param message - The error message to display
     */
    public void showError(String message) {
        System.out.println(ECHO_LINE);
        System.out.println("    Oops! " + message.replace("\n", "\n    "));
        System.out.println(ECHO_LINE);
    }

    /**
     * Displays an error message when there's a problem loading the task file.
     * Informs the user that the program will start with an empty task list.
     */
    public void showLoadingError() {
        showError("Problem loading tasks from file. Starting with an empty task list.");
    }
}
