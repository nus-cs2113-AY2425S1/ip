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
     * The format of the output is adjusted depending on whether the tasks have been modified.
     *
     * @param isModified Boolean flag indicating whether the task list has been modified.
     */
    public void showAllTasks(boolean isModified) {
        String extraSpace = (isModified ? "\t" : "");
        String space = extraSpace + "\t";
        for (Task task : fenix.getTaskArrayList()) {
            String index = (fenix.indexOfTask(task) + 1) + ". ";
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
        System.out.println(modification[0]);
        printFenixModification(modification[1], task);
    }

    /**
     * Prints the details of the task modification, including the modification type and the task itself.
     *
     * @param modification The type of modification (e.g., "added", "deleted").
     * @param task The task that has been modified.
     */
    private void printFenixModification(String modification, Task task) {
        int taskIndex = fenix.indexOfTask(task) + 1;
        String taskNumber = (taskIndex == 0 ? "" : taskIndex + ". ");
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + modification + taskNumber + task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("You now have " + fenix.getSize() + " tasks awaiting your attention.");
    }

    /**
     * Requests the user to provide a command.
     */
    public void requestForCommand() {
        System.out.println("Please provide a command");
    }

    /**
     * Requests the user to provide a valid command when the given command is invalid.
     */
    public void requestForValidCommand() {
        System.out.println("Please provide a valid command");
    }

    /**
     * Requests the user to provide a task description when it is missing.
     */
    public void requestForTask() {
        System.out.println("Please provide a task");
    }

    /**
     * Requests the user to provide a valid task number when the input is invalid.
     */
    public void requestForValidTask() {
        System.out.println("Please provide a valid task number");
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
