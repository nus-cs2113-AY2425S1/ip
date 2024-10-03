import java.util.Scanner;
import java.util.ArrayList;
/**
 * Handles the user interface interactions for the task management application.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm POIROT\nWhat can I do for you?");
        showLine();
    }
    /**
     * Displays a goodbye message when exiting the application.
     */
    public void showExit() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task     The task that was added.
     * @param taskCount The current count of tasks in the list.
     */
    public void showAddedTask(Task task, int taskCount) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        showLine();
    }
    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task     The task that was deleted.
     * @param taskCount The current count of tasks in the list.
     */
    public void showDeletedTask(Task task, int taskCount) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        showLine();
    }
    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks     The list of tasks to display.
     * @param lastIndex The count of tasks in the list.
     */
    public void showTaskList(Task[] tasks, int lastIndex) {
        showLine();
        if (lastIndex == 0) {
            System.out.println("No actions available");
        } else {
            for (int i = 0; i < lastIndex; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        showLine();
    }
    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.print("[" + task.getStatusIcon() + "] ");
        System.out.println(task.getDescription());
        showLine();
    }
    /**
     * Displays a message indicating that a task has been unmarked as done.
     *
     * @param task The task that was unmarked as done.
     */
    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.print("[" + task.getStatusIcon() + "] ");
        System.out.println(task.getDescription());
        showLine();
    }
    /**
     * Displays the tasks found that match the keyword.
     *
     * @param tasks The list of found tasks to display.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
}
