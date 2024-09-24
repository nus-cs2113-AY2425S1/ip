package codecatalyst;

import codecatalyst.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * This class is responsible for reading user input and printing messages to the console.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next user command from the input.
     *
     * @return The user's input command as a {@code String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a divider line to the console.
     * This is used to visually separate sections of output.
     */
    public void printDivider() {
        System.out.println("        ________________________________________________________\n");
    }

    public void printGreeting() {
        printDivider();
        System.out.println("         Hello, I'm CodeCatalyst. ");
        System.out.println("         What can I do for you?");
        printDivider();
    }

    public void printGoodbye() {
        System.out.println("         Bye. Hope to see you again soon!");
    }

    public void printError(String errormessage) {
        System.out.println("Error: " + errormessage);
    }

    public void printLoadingError() {
        System.out.println("Error loading tasks from the file");
    }

    /**
     * Prints a confirmation message after adding a task to the list.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printTaskAdded(Task task, int size) {
        System.out.println("         Got it. I've added this task:");
        System.out.println("         " + task);
        System.out.println("         Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void printTaskMarked(Task task) {
        System.out.println("         Nice! I've marked this task as done:");
        System.out.println("         " + task);
    }

    /**
     * Prints a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that has been unmarked.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println("         Nice! I've unmarked this task as done:");
        System.out.println("         " + task);
    }

    /**
     * Prints a confirmation message after deleting a task from the list.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list after deletion.
     */
    public void printTaskDeleted(Task task, int size) {
        System.out.println("         Noted. I've removed this task:");
        System.out.println("           " + task);
        System.out.println("         Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the entire task list.
     * Each task is printed with its index in the list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("         Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("         " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the tasks that match the specified keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     * @param keyword The keyword used for the search.
     */
    public void printMatchingTasks(ArrayList<Task> tasks, String keyword) {
        System.out.println("         Tasks that match the keyword \"" + keyword + "\" is shown in the following list.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("         " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints a message indicating that no tasks matched the specified keyword.
     */
    public void printNoMatchingTask() {
        System.out.println("         No task found matching the keyword.");
    }


}
