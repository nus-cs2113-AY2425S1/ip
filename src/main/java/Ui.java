import java.util.Scanner;
import java.util.ArrayList;


/**
 * The Ui class handles the user interface for the task management application.
 * It provides methods for displaying messages and interacting with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line to the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("I'm Shrek");
        System.out.println("What are you doing in my swamp!??");
        showLine();
    }

    /**
     * Reads a command inputted by the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message when no previous task data is found.
     */
    public void showLoadingError() {
        System.out.println("No previous task data found. Starting with an empty list.");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task     the task that was added
     * @param taskCount the total number of tasks after addition
     */
    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println("I've added this task to your pile of nonsense:");
        System.out.println("   " + task);
        System.out.println("Now you have " + taskCount + " tasks in my swamp.");
        showLine();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks the TaskList containing the current tasks
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println("Here is your list of nonsense, now get out of my swamp:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));  // Utilizes Task's toString() method
        }
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("It’s done. Now, get lost before I toss you out of my swamp!");
        System.out.println("     [" + task.getStatusIcon() + "] " + task.getDescription());
        showLine();
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("It’s unmarked. Gonna change it again? Make up your mind, or get out of my swamp!");
        System.out.println("     [" + task.getStatusIcon() + "] " + task.getDescription());
        showLine();
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     */
    public void showTaskDeleted(Task task) {
        showLine();
        System.out.println("Fine I’ll remove this task donkey:");
        System.out.println("   " + task);
        showLine();
    }


    /**
     * Displays a format error message when the user input is incorrect.
     */
    public void showFormatError() {
        showLine();
        System.out.println("Wrong format, enter a valid task.");
        showLine();
    }

    /**
     * Displays an index error message when the user tries to access a non-existent task.
     */
    public void showIndexError() {
        showLine();
        System.out.println("That task is not even in my swamp donkey! Try another task.");
        showLine();
    }

    /**
     * Displays an input error message when the user input is invalid.
     */
    public void showInputError() {
        showLine();
        System.out.println("What are you typing donkey? Specify the time/task right or get out of my swamp.");
        showLine();
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Finally, you’re leaving. Now I can have some peace in my swamp.");
        showLine();
    }

    /**
     * Displays an error message when saving a file fails.
     */
    public void showSaveError(){
        showLine();
        System.out.println("Failed to save file.");
        showLine();
    }

}

