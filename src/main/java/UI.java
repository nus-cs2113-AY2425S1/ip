import java.util.List;
import java.util.Scanner;

/**
 * The UI class is responsible for handling all user interactions.
 * It shows messages, receives input, and displays tasks.
 */
public class UI {
    private Scanner scanner;

    /**
     * Constructor for UI. Initializes the Scanner to capture user input.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm ANDY");
    }

    /**
     * Gets the user input from the console.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the goodbye message when the program ends.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks that match the user's search keyword.
     *
     * @param tasks The list of tasks matching the search criteria.
     */
    public void showFoundTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param description The description of the added task.
     */
    public void showTaskAddedMessage(String description) {
        System.out.println("Added: " + description);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param description The description of the deleted task.
     */
    public void showTaskDeletedMessage(String description) {
        System.out.println("Deleted: " + description);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + task.getDescription());
    }

    /**
     * Displays a message when a task is unmarked (marked as not done).
     *
     * @param task The task that has been unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + task.getDescription());
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The TaskList containing all tasks.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }

    /**
     * Displays an error message when an exception or error occurs.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}