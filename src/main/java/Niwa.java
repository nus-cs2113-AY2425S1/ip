import java.util.ArrayList;
import java.util.List;

/**
 * Niwa chatbot class that processes user commands to manage a task list.
 */
public class Niwa {

    // Static variables to hold the chatbot's name and logo
    static final String NAME = "Niwa";
    static final String LOGO = "\t _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | __  // //| |\n"
            + "\t| |\\  | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";

    // Static variables to format messages
    private static final String PREFIX = "\t";
    private static final String SEPARATOR = PREFIX + "---------------------------------------------";

    private static final String HI_MESSAGE = PREFIX + "Hello sweeties! I'm %s!\n"
            + PREFIX + "What can I do for you? Let's chat <3\n\n";

    // Static variables to hold the command information
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";

    private static final String COMMAND_EVENT_DESC = "event [task description] /from [time] /to [time]: "
            + "Add a new event to our list.";
    private static final String COMMAND_TODO_DESC = "todo [task description]: "
            + "Add a new to-do task to our list.";
    private static final String COMMAND_DEADLINE_DESC = "deadline [task description] /by [time]:"
            + "Add a new deadline to our list.";
    private static final String COMMAND_ADD_SUCCESS = "Got it. I've added this task:%n"
            + PREFIX + "   %s%n"
            + PREFIX + "You currently have %d tasks in the list.%n";

    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_DELETE_DESC = "delete [task index]: Delete the task at the given index.";
    private static final String COMMAND_DELETE_SUCCESS = "OK, I've deleted this task:%n"
            + PREFIX + "   %s%n"
            + PREFIX + "You currently have %d tasks in the list.%n";

    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_MARK_DESC = "mark [task index]: Mark the task at the given index as done.";
    private static final String COMMAND_MARK_SUCCESS = "OK, I've marked this task as done:%n"
            + PREFIX + "%s%n";

    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_UNMARK_DESC = "unmark [task index]: Mark the task at the given index as undone.";
    private static final String COMMAND_UNMARK_SUCCESS = "OK, I've marked this task as not done yet:%n"
            + PREFIX + "%s%n";

    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_LIST_DESC = "list: List all current tasks.";
    private static final String COMMAND_LIST_SUCCESS = "Here are the tasks in your list:";

    private static final String COMMAND_BYE_WORD = "bye";
    private static final String COMMAND_BYE_DESC = "bye: End the program.";
    private static final String COMMAND_BYE_SUCCESS = "Bye bae. Hope to see you again soon! Moah~";

    private static final String COMMAND_HELP_WORD = "help";
    private static final String COMMAND_HELP_DESC = "help: Print this guide.";
    private static final String COMMAND_GUIDE = PREFIX + COMMAND_EVENT_DESC + "\n"
            + PREFIX + COMMAND_TODO_DESC + "\n"
            + PREFIX + COMMAND_DEADLINE_DESC + "\n"
            + PREFIX + COMMAND_DELETE_DESC + "\n"
            + PREFIX + COMMAND_MARK_DESC + "\n"
            + PREFIX + COMMAND_UNMARK_DESC + "\n"
            + PREFIX + COMMAND_LIST_DESC + "\n"
            + PREFIX + COMMAND_BYE_DESC + "\n"
            + PREFIX + COMMAND_HELP_DESC;

    // Static variables for error messages
    private static final String ERR_INDEX_OUT_OF_BOUND = "Index is out of bound!";
    private static final String ERR_LACK_ARGUMENT = "Not enough arguments! Please check your syntax.";
    private static final String ERR_INDEX_NUMBER_FORMAT = "Task's index must be a number!";

    /** Variable to check if the chatbot is running */
    private boolean isRunning;

    /** List to store tasks */
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
        processCommand(COMMAND_HELP_WORD);
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
            case COMMAND_EVENT_WORD:
                // Fall through
            case COMMAND_DEADLINE_WORD:
                // Fall through
            case COMMAND_TODO_WORD:
                add(commandParts[1], commandParts[0]);
                break;
            default:
                echo(command);
                break;
            }
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Lists all tasks in the task list.
     */
    private void list() {
        System.out.println(PREFIX + COMMAND_LIST_SUCCESS);
        int index = 1;

        for (Task task : tasks) {
            System.out.printf(PREFIX + "%d. %s%n", index++, task.getFullInfo());
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param taskInfo The task details to add.
     * @param type The type of task to add ("event", "deadline", "todo").
     */
    private void add(String taskInfo, String type) {
        Task temp;
        try {
            String[] taskArguments;
            switch (type) {
            case COMMAND_EVENT_WORD:
                taskArguments = Event.getArgument(taskInfo);
                if (taskArguments == null) {
                    throw new IndexOutOfBoundsException();
                }
                temp = new Event(taskArguments[0], taskArguments[1], taskArguments[2]);
                break;
            case COMMAND_DEADLINE_WORD:
                taskArguments = Deadline.getArgument(taskInfo);
                if (taskArguments == null) {
                    throw new IndexOutOfBoundsException();
                }
                temp = new Deadline(taskArguments[0], taskArguments[1]);
                break;
            case COMMAND_TODO_WORD:
                temp = new ToDo(taskInfo);
                break;
            default:
                temp = new Task(taskInfo);
                break;
            }

            tasks.add(temp);
            System.out.printf(PREFIX + COMMAND_ADD_SUCCESS, temp.getFullInfo(), tasks.size());

        } catch (IndexOutOfBoundsException e) {
            System.out.println(PREFIX + ERR_LACK_ARGUMENT);
        }
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
            int index = Integer.parseInt(indexString) - 1; // Convert to zero-based index
            Task taskToMark = tasks.get(index);
            taskToMark.markAsDone();

            System.out.printf(PREFIX + COMMAND_MARK_SUCCESS, taskToMark.getFullInfo());

        } catch (IndexOutOfBoundsException e) {
            System.out.println(PREFIX + ERR_INDEX_OUT_OF_BOUND);
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + ERR_INDEX_NUMBER_FORMAT);
        }
    }

    /**
     * Marks a task as not done yet.
     *
     * @param indexString The index of the task to unmark.
     */
    private void unmark(String indexString) {
        try {
            int index = Integer.parseInt(indexString) - 1; // Convert to zero-based index
            Task taskToUnmark = tasks.get(index);
            taskToUnmark.markAsUndone();

            System.out.printf(PREFIX + COMMAND_UNMARK_SUCCESS, taskToUnmark.getFullInfo());

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
            int index = Integer.parseInt(indexString) - 1; // Convert to zero-based index
            Task task = tasks.get(index);
            tasks.remove(index);

            System.out.printf(PREFIX + COMMAND_DELETE_SUCCESS, task.getFullInfo(), tasks.size());

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
    }
}
