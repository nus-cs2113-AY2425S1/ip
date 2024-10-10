package UI;

import java.util.Scanner;
import task.Task;
import task.TaskList;

/**
 * Handles interactions with the user.
 * <p>
 * This class provides methods to display messages to the user, read user input, and format the output for a better user experience.
 */
public class Ui {
    private static final String INDENTATION = "    ";

    /**
     * Displays a welcome message and the logo of the chatbot.
     */
    public void showWelcome() {
        String LOGO = """
                      ██▓     ██▓ ▄▄▄
                      ▓██▒    ▓██▒▒████▄
                      ▒██░    ▒██▒▒██  ▀█▄
                      ▒██░    ░██░░██▄▄▄▄██
                      ░██████▒░██░ ▓█   ▓██▒
                      ░ ▒░▓  ░░▓   ▒▒   ▓▒█░
                      ░ ░ ▒  ░ ▒ ░  ▒   ▒▒ ░
                      ░ ░    ▒ ░  ░   ▒
                          ░  ░ ░        ░  ░
                      """;
        System.out.println(INDENTATION + "Hello! I'm \n" + LOGO);
        System.out.println(INDENTATION + "What can I do for you?");
        printLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return The command input by the user as a trimmed string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    /**
     * Prints a line separator for better readability.
     */
    public void printLine() {
        System.out.println(INDENTATION + "___________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task     The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void addTaskAndPrint(Task task, int taskCount) {
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + task.toString());
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task     The task that was removed.
     * @param taskCount The current number of tasks in the list.
     */
    public void showDeleteTask(Task task, int taskCount) {
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + task.toString());
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints all tasks in the provided TaskList.
     *
     * @param tasks The TaskList containing tasks to be printed.
     */
    public void printTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(INDENTATION + "No tasks found.");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkDone(Task task) {
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task.toString());
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkDone(Task task) {
        System.out.println(INDENTATION + "OK, I've unmarked this task:");
        System.out.println(INDENTATION + task.toString());
    }

    /**
     * Displays a farewell message when the application is exiting.
     */
    public void showFarewell() {
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(INDENTATION + message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        System.out.println(INDENTATION + "Error loading tasks from file.");
    }
}
