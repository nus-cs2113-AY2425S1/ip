import java.util.List;
import java.util.Scanner;

/**
 * The UserInterface class handles interactions between the user and the Fenix application.
 * It manages input and output, displaying information and prompts to the user,
 * and collecting user input through the console.
 */
public class UserInterface implements SampleStrings {
    private Fenix fenix;
    private Scanner scanner;

    /**
     * Constructs a UserInterface for a given Fenix instance.
     * Initializes the scanner to read user input.
     *
     * @param fenix The Fenix instance that this UserInterface interacts with.
     */
    public UserInterface(Fenix fenix) {
        this.fenix = fenix;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user when the application starts.
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        System.out.println(GREETING);
        System.out.println(SERVICE_PROMPT);
    }

    /**
     * Reads and returns the user's input from the console.
     *
     * @return The user input as a string.
     */
    public String getUserInput() {
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        return scanner.nextLine();
    }

    /**
     * Displays all tasks to the user.
     * The tasks are displayed with their index in the list and formatted accordingly.
     *
     * @param taskArrayList The list of tasks to be displayed.
     */
    public void showAllTasks(List<Task> taskArrayList) {
        String space = "\t";
        for (Task task : taskArrayList) {
            String index = (taskArrayList.indexOf(task) + 1) + ". ";
            System.out.println(space + index + task);
        }
    }

    /**
     * Displays a message to indicate a task modification by Fenix (e.g., task added, marked, or deleted).
     *
     * @param modification Array containing messages to display for the modification.
     * @param task The task that has been modified.
     */
    public void showFenixModification(String[] modification, Task task) {
        if (task == null) {
            return;
        }
        String modificationMessage = modification[0];
        String modificationType = modification[1];
        System.out.println(modificationMessage);
        printFenixModification(modificationType, task);
    }

    /**
     * Prints the details of the task modification, including the modification type and the task itself.
     * Also displays the number of unfinished tasks remaining and a special message if all tasks are done.
     *
     * @param modificationType The type of modification (e.g., "added", "deleted").
     * @param task The task that has been modified.
     */
    private void printFenixModification(String modificationType, Task task) {
        int taskIndex = fenix.indexOfTask(task) + 1;
        // taskIndex == 0 when task has been deleted
        String taskNumber = (taskIndex == 0 ? "" : taskIndex + ". ");
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + modificationType + taskNumber + task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        int numberOfUnfinishedTasks = fenix.getNumberOfUnfinishedTasks();
        System.out.println("You now have " + numberOfUnfinishedTasks + " tasks awaiting your attention.");
        if (numberOfUnfinishedTasks == 0) {
            printAllTasksDone();
        }
    }

    private void printAllTasksDone() {
        System.out.println(ALL_TASKS_COMPLETED);
    }

    public void printMatchedTasks() {
        System.out.println(MATCHED_TASKS);
    }

    /**
     * Requests the user to provide a command.
     */
    public void requestForCommand() {
        System.out.println(COMMAND_REQUEST);
    }

    /**
     * Requests the user to provide a valid command when the given command is invalid.
     */
    public void requestForValidCommand() {
        System.out.println(VALID_COMMAND_REQUEST);
    }

    /**
     * Requests the user to provide a task description when it is missing.
     */
    public void requestForTask() {
        System.out.println(TASK_REQUEST);
    }

    /**
     * Requests the user to provide a valid task number when the input is invalid.
     */
    public void requestForValidTask() {
        System.out.println(VALID_TASK_REQUEST);
    }

    /**
     * Bids farewell to the user when the application is closed.
     * Closes the scanner to release resources.
     */
    public void bidFarewell() {
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        scanner.close();
    }
}
