import task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles user interface interactions and displays messages to the user.
 */
public class UI {

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks the list of tasks to display
     */
    public static void displayTasks(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task that was marked as done
     */
    static void displayTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task the task that was marked as not done
     */
    static void displayTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param tasks the list of tasks
     * @param task the task that was added
     */
    static void displayTaskAdded(List<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param tasks the list of tasks
     * @param task the task that was deleted
     */
    static void displayTaskDeleted(List<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when tasks are successfully loaded from a file.
     */
    static void displayLoadSuccessMessage() {
        System.out.println("Loaded tasks from file successfully.");
    }

    /**
     * Reads a command from the user.
     *
     * @return a Scanner object to read user input
     */
    static Scanner readCommand() {
        return new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    static void displayWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Flash");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the farewell message to the user.
     */
    static void displayByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a list of tasks that match the user's search query.
     *
     * @param matchedTasks the list of matching tasks
     */
    public static void displayMatchedTasks(List<Task> matchedTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            System.out.println(i + 1 + "." + matchedTasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
