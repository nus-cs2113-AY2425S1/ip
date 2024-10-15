import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user,
 * including displaying messages and reading user input.
 */
public class Ui {
    private Scanner inputScanner;

    /**
     * Initializes the {@code Ui} with a new {@code Scanner} for user input.
     */
    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Welcome aboard AirBorder.");
        System.out.println(" Ready to assist you with your tasks!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Thank you for flying with AirBorder!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads and returns the user's input command.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return inputScanner.nextLine().trim();
    }

    /**
     * Displays a divider line for better readability.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" ERROR: " + message);
        showLine();
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of tasks.
     */
    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println(" Task added: " + task);
        System.out.println(" Now you have " + taskCount + " tasks.");
        showLine();
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The current number of tasks.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        showLine();
        System.out.println(" Task deleted: " + task);
        System.out.println(" Now you have " + taskCount + " tasks.");
        showLine();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskDone(Task task) {
        showLine();
        System.out.println(" Task completed: " + task);
        showLine();
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUndone(Task task) {
        showLine();
        System.out.println(" Task marked as incomplete: " + task);
        showLine();
    }

    /**
     * Displays the list of all tasks to the user.
     *
     * @param taskList The {@code TaskList} containing all tasks.
     */
    public void showTaskList(TaskList taskList) {
        showLine();
        if (taskList.isEmpty()) {
            System.out.println(" No tasks in your list.");
        } else {
            System.out.println(" Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                try {
                    System.out.println(" " + (i + 1) + "." + taskList.getTask(i));
                } catch (AirBorderException e) {
                    showError(e.getMessage());
                }
            }
        }
        showLine();
    }

    /**
     * Displays an error message if tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        showLine();
        System.out.println(" Error loading tasks from file.");
        showLine();
    }

    /**
     * Displays the tasks that match the search keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : matchingTasks) {
                System.out.println(" " + index + "." + task);
                index++;
            }
        }
        showLine();
    }

    /**
     * Closes the {@code Scanner} resource.
     */
    public void close() {
        inputScanner.close();
    }
}
