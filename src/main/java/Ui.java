import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "//" + "\u2500".repeat(50);
    private static final String ECHO_LINE = "    " + "\u2500".repeat(48);
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Welcome message that runs upon program starting.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm PlopBot.");
        System.out.println("What can I do for you today?\n");
    }

    /**
     * Exit message that runs upon program ending.
     */
    public void showExit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Thank you for choosing PlopBot. Have a great day!\n");
    }

    /**
     * Reads the user's input and cleans it for processing.
     * @return
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Following methods are the program's responses to the user's commands.
     * @param tasks
     */
    public void showTasks(ArrayList<Task> tasks) {
        System.out.println(ECHO_LINE);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(ECHO_LINE);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(ECHO_LINE);
        System.out.println("    Added: " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println(ECHO_LINE);
    }

    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println(ECHO_LINE);
        System.out.println("    Removed: " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println(ECHO_LINE);
    }

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
     * Following two methods mark and unmark tasks as complete/incomplete.
     * @param task
     */
    public void showTaskMarked(Task task) {
        System.out.println("    I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("    I've unmarked this task:");
        System.out.println("  " + task.toString());
    }

    /**
     * Prints relevant error message.
     * @param message
     */
    public void showError(String message) {
        System.out.println(ECHO_LINE);
        System.out.println("    Oops! " + message.replace("\n", "\n    "));
        System.out.println(ECHO_LINE);
    }

    /**
     * File loading error message.
     */
    public void showLoadingError() {
        showError("Problem loading tasks from file. Starting with an empty task list.");
    }
}