package tars.userinterface;

import tars.task.Task;
import java.util.List;

/**
 * Handles the interactions with the user by displaying messages, tasks, and errors.
 */
public class UserInterface {

    /**
     * Prints a separator line for visual clarity.
     */
    public void printSeparator() {
        System.out.println("    " + "------------------------------------------------------------");
    }

    /**
     * Displays a welcome message when the program starts.
     */
    public void showWelcomeMessage() {
        printSeparator();
        System.out.println("    Hello! I'm tars.tars.");
        System.out.println("    Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        printSeparator();
    }

    /**
     * Displays the list of tasks or shows a message if the task list is empty.
     *
     * @param taskList The list of tasks to display.
     */
    public void showTasks(List<Task> taskList) {
        printSeparator();
        if (taskList.isEmpty()) {
            System.out.println("    Your task list is empty. Looks like you have nothing to do... for now.");
        } else {
            System.out.println("    Here are your tasks. If you're planning world domination, you're off to a slow start: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + taskList.get(i));
            }
        }
        printSeparator();
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int taskCount) {
        printSeparator();
        System.out.println("    Got it. I've added this task: ");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskDone(Task task) {
        printSeparator();
        System.out.println("    Great! Task marked as complete: ");
        System.out.println("    " + task);
        printSeparator();
    }

    /**
     * Displays a message when a task is unmarked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskNotDone(Task task) {
        printSeparator();
        System.out.println("    Task has been unmarked: ");
        System.out.println("    " + task);
        printSeparator();
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The total number of tasks left in the list after deletion.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        printSeparator();
        System.out.println("    Noted. I've successfully removed this task: ");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskCount + " tasks left in your list.");
        printSeparator();
    }

    /**
     * Displays an error message when an invalid input is detected.
     */
    public void showInvalidInputMessage() {
        printSeparator();
        System.out.println("    That input didn't quite compute. Try again, or I'll have to assume you're speaking in code.");
        printSeparator();
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        printSeparator();
        System.out.println("    Error loading tasks from file.");
        printSeparator();
    }

    /**
     * Displays a goodbye message when the program exits.
     */
    public void showGoodbyeMessage() {
        printSeparator();
        System.out.println("    Oh, leaving already? Fine, I will just sit here calculating the probability of you returning. It's... pretty high.");
        printSeparator();
    }

    /**
     * Displays a custom error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        printSeparator();
        System.out.println("    Error: " + message);
        printSeparator();
    }
}
