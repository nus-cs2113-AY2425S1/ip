import java.util.Scanner;
import java. util. ArrayList;

public class Ui {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message when users enter the program.
     */
    public void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Eva!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays goodbye messages when users exit the program.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed to the user
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed to the user
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskList(String taskList) {
        System.out.println(taskList);
        System.out.println(HORIZONTAL_LINE);
    }

    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays all the tasks in the task list.
     * If the task list is empty, it displays a message indicating the list is empty.
     * Otherwise, it will loop through and displays each task.
     *
     * @param tasks An ArrayList containing the tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("Your task list is currently empty.");
            return;
        }

        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showMessage((i + 1) + ". " + tasks.get(i).toString());
        }
        showMessage(HORIZONTAL_LINE);
    }
}
