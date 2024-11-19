package Taylor.command;

import Taylor.task.Task;
import Taylor.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface for the Taylor task management application.
 * The Ui class handles user interaction, including input and output.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc;

    /**
     * Constructs a new Ui object with a Scanner for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        printLogo();
        System.out.println(LINE);
        System.out.println("🤖 Hello! I'm Taylor, your friendly chatbot assistant! 🎉");
        System.out.println(LINE);
        printManual();
        System.out.println(LINE);
        System.out.println("How can I assist you today? 😊");
        System.out.println(LINE);
    }

    /**
     * Displays the logo the chatbot Taylor
     */
    private void printLogo() {
        String logo =
        """
              _____
             /     \\
            |  O O  |
            |   ^   |
            |  '-'  |
             \\_____/
              | | |
             /|_|_|\\
            /  | |  \\
               / \\
              /   \\
        Your Personal Task Manager Bot Taylor💼""";
        System.out.println(logo);
    }

    /**
     * Displays the manual of commands of Taylor
     */
    private void printManual() {
        System.out.println("📝 **Here's what I can do:**");
        System.out.println("1. `todo <task>` - Add a new todo.");
        System.out.println("2. `event <desc> /from <start> /to <end>` - Schedule an event.");
        System.out.println("3. `deadline <desc> /by <date> [dd-MM-yyyy]` - Set a deadline.");
        System.out.println("4. `list` or `ls` - Show all your tasks.");
        System.out.println("5. `mark <num>` - Mark a task as done.");
        System.out.println("6. `unmark <num>` - Mark a task as not done.");
        System.out.println("7. `delete <num>` - Remove a task.");
        System.out.println("8. `find <keyword>` - Search for tasks.");
        System.out.println("9. `clear` - Remove all tasks.");
        System.out.println("10. `bye` - Exit our chat. See you next time! 👋");
    }


    /**
     * Displays the goodbye message when the user exits the program.
     */
    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints a line separator for better readability in the output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message if there is an issue loading the data from the file.
     */
    public void showLoadingError() {
        System.out.println("Unable to load the file.");
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays all tasks currently in the TaskList.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTasks(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been added to the TaskList.
     *
     * @param task The task that was added.
     * @param tasks The TaskList containing all tasks.
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been removed from the TaskList.
     *
     * @param task The task that was removed (as a String).
     * @param tasks The TaskList containing the remaining tasks.
     */
    public void showTaskDeleted(String task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays tasks that matching the keyword found from the TaskList
     *
     * @param result The String of matching tasks
     */
    public void showFindResult(String result){
        if (result == null) {
            System.out.println("There is no matching task found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(result);
        }
    }

    /**
     * Displays a message within a line separator for readability.
     *
     * @param message The message to be displayed.
     */
    public void println(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}