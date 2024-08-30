import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Niwa chatbot class that processes user commands to manage a task list [Level-3].
 */
public class Niwa {

    /** Static variables to hold the chatbot's name and logo*/
    static final String NAME = "Niwa";
    static final String LOGO = "\t _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";

    /** Static variables to format **/
    private static final String PREFIX = "\t";
    private static final String SEPARATOR = PREFIX + "---------------------------------------------";

    private static final String HI_MESSAGE = PREFIX + "Hello sweeties! I'm %s!\n"
            + PREFIX + "What can I do for you? Let's chat <3\n\n";
    /** Static variables to hold the commands **/
    private static final String COMMAND_ADD_WORD = "add";
    private static final String COMMAND_ADD_DESC = "add [task description]: Add a new task to our list.";
    private static final String COMMAND_ADD_SUCCESS = "added: %s";

    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_DELETE_DESC = "delete [task index]: Delete the task at the given index.";
    private static final String COMMAND_DELETE_SUCCESS = "OK, I've deleted this task: %s%n";

    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_MARK_DESC = "mark [task index]: Mark the task at the given index as done.";
    private static final String COMMAND_MARK_SUCCESS = "OK, I've marked this task as done: %s %s%n";

    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_UNMARK_DESC = "unmark [task index]: Mark the task at the given index as undone.";
    private static final String COMMAND_UNMARK_SUCCESS = "OK, I've marked this task as not done yet: %s %s%n";

    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_LIST_DESC = "list: List all current tasks.";
    private static final String COMMAND_LIST_SUCCESS = "Here are the tasks in your list:";

    private static final String COMMAND_BYE_WORD = "bye";
    private static final String COMMAND_BYE_DESC = "bye: End the program.";
    private static final String COMMAND_BYE_SUCCESS = "Bye bae. Hope to see you again soon! Moah~";

    private static final String COMMAND_HELP_WORD = "help";
    private static final String COMMAND_HELP_DESC = "help: Print this guide.";
    private static final String COMMAND_GUIDE = PREFIX + COMMAND_ADD_DESC + "\n"
            + PREFIX + COMMAND_DELETE_DESC + "\n"
            + PREFIX + COMMAND_MARK_DESC + "\n"
            + PREFIX + COMMAND_UNMARK_DESC + "\n"
            + PREFIX + COMMAND_LIST_DESC + "\n"
            + PREFIX + COMMAND_BYE_DESC + "\n"
            + PREFIX + COMMAND_HELP_DESC;

    /** Static variables to alert errors **/
    private static final String ERR_INDEX_OUT_OF_BOUND = "Index is out of bound!";
    private static final String ERR_INDEX_NUMBER_FORMAT = "Task's index must be a number!";

    /** Variable to check if the chatbot is running*/
    private boolean isRunning;

    /** List to store tasks*/
    private List<Task> tasks = new ArrayList<>();

    /**
     * Getter for the chatbot's name.
     *
     * @return The name of the chatbot.
     */
    public static String getName() {
        return NAME;
    }

    /**
     * Getter for the chatbot's logo.
     *
     * @return The logo of the chatbot.
     */
    public static String getLogo() {
        return LOGO;
    }

    /**
     * Getter for the isRunning flag.
     *
     * @return True if the chatbot is running, false otherwise.
     */
    public boolean getRunning() {
        return isRunning;
    }

    /**
     * Constructor initializes the chatbot and displays a greeting and user guide.
     */
    public Niwa() {
        isRunning = true;
        printGreet(NAME, LOGO);
        help();
    }

    /**
     * Processes the command entered by the user.
     *
     * @param command The user command to process.
     */
    public void processCommand(String command) {
        System.out.println(SEPARATOR);

        command = command.trim();
        String[] commandParts = command.split(" ", 2);

        if (commandParts.length == 1) { // Handle single-word commands
            switch (commandParts[0]) {
            case COMMAND_BYE_WORD:
                printExit();
                isRunning = false;
                break;
            case COMMAND_LIST_WORD:
                list();
                break;
            case COMMAND_HELP_WORD:
                help();
                break;
            default:
                echo(command);
                break;
            }
        } else if (commandParts.length == 2) { // Handle commands with arguments
            switch (commandParts[0]) {
            case COMMAND_MARK_WORD:
                mark(commandParts[1]);
                break;
            case COMMAND_UNMARK_WORD:
                unmark(commandParts[1]);
                break;
            case COMMAND_DELETE_WORD:
                delete(commandParts[1]);
                break;
            case COMMAND_ADD_WORD:
                add(commandParts[1]);
                break;
            default:
                echo(command);
                break;
            }
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    private void list() {
        System.out.println(PREFIX + COMMAND_LIST_SUCCESS);
        int index = 1;

        for (Task task : tasks) {
            System.out.printf("\t%d.%s %s%n", index++, task.getStatusIcon(), task.getDescription());
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add.
     */
    private void add(String task) {
        Task temp = new Task(task);
        tasks.add(temp);
        System.out.printf(PREFIX + COMMAND_ADD_SUCCESS, temp.getDescription() + "\n");
    }

    /**
     * Echoes the user's command back to them.
     *
     * @param command The command to echo.
     */
    private void echo(String command) {
        System.out.println(PREFIX + command);
    }

    /**
     * Marks a task as done.
     *
     * @param indexString The index of the task to mark.
     */
    private void mark(String indexString) {
        try {
            int index = Integer.parseInt(indexString);
            index = index - 1; /* Convert to zero-based index */
            Task taskToMark = tasks.get(index);
            taskToMark.markAsDone();

            System.out.printf(PREFIX + COMMAND_MARK_SUCCESS, taskToMark.getStatusIcon(), taskToMark.getDescription());

        } catch (IndexOutOfBoundsException e) {
            System.out.println(PREFIX + ERR_INDEX_OUT_OF_BOUND);
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + ERR_INDEX_NUMBER_FORMAT);
        }
    }

    /**
     * Marks a task as not done yet.
     *
     * @param indexString The index of the task to mark.
     */
    private void unmark(String indexString) {
        try {
            int index = Integer.parseInt(indexString);
            index = index - 1; /* Convert to zero-based index */
            Task taskToMark = tasks.get(index);
            taskToMark.markAsUndone();

            System.out.printf(PREFIX + COMMAND_UNMARK_SUCCESS, taskToMark.getStatusIcon(), taskToMark.getDescription());

        } catch (IndexOutOfBoundsException e) {
            System.out.println(PREFIX + ERR_INDEX_OUT_OF_BOUND);
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + ERR_INDEX_NUMBER_FORMAT);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param indexString The index of the task to delete.
     */
    private void delete(String indexString) {
        try {
            int index = Integer.parseInt(indexString);
            index = index - 1; /* Convert to zero-based index */
            Task task = tasks.get(index);
            tasks.remove(index);

            System.out.printf(PREFIX + COMMAND_DELETE_SUCCESS, task.getDescription());
            list(); // Display the updated task list

        } catch (IndexOutOfBoundsException e) {
            System.out.println(PREFIX + ERR_INDEX_OUT_OF_BOUND);
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + ERR_INDEX_NUMBER_FORMAT);
        }
    }

    /**
     * Prints a user guide.
     */
    private void help() {
        System.out.println(COMMAND_GUIDE);
    }

    /**
     * Prints a greeting message when the chatbot starts.
     *
     * @param name The name of the chatbot.
     * @param logo The logo of the chatbot.
     */
    public void printGreet(String name, String logo) {
        System.out.println(logo);
        System.out.println(SEPARATOR);
        System.out.printf(HI_MESSAGE, name);
    }

    /**
     * Prints a farewell message.
     */
    public void printExit() {
        System.out.println(PREFIX + COMMAND_BYE_SUCCESS);
        System.out.println(SEPARATOR);
    }

    /**
     * Retrieves the next command from the user.
     *
     * @return The command input by the user.
     */
    public String getCommand() {
        System.out.println(SEPARATOR);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
