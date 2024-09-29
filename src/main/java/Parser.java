/**
 * The Parser class handles the processing and validation of user input.
 * It interacts with the TaskList and UI to manage tasks and display appropriate responses.
 * It also checks for input validity and throws exceptions where necessary.
 */
public class Parser {

    // Reference to the TaskList instance
    private TaskList taskListInstance;

    // Reference to the UI instance
    private UI uiInstance;

    /**
     * Constructor for the Parser.
     * Associates the Parser with TaskList and UI instances to handle input processing and display.
     *
     * @param taskList The TaskList instance to be used by the Parser.
     * @param uiInstance The UI instance to be used for displaying messages.
     */
    public Parser(TaskList taskList, UI uiInstance) {
        this.taskListInstance = taskList;
        this.uiInstance = uiInstance;
    }

    /**
     * Checks the validity of ToDo input.
     * Throws a DukeException if the input is empty or null.
     *
     * @param input The description of the ToDo task.
     * @throws DukeException If the input is invalid.
     */
    public void checkTodoInput(String input) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            uiInstance.Warning("Description for a todo cannot be empty");
        }
    }

    /**
     * Checks the validity of Deadline input.
     * Throws a DukeException if the description or date input is invalid.
     *
     * @param input The description of the Deadline task.
     * @param state The state indicating whether the "/by" date was provided correctly.
     * @throws DukeException If the input is invalid or incomplete.
     */
    public void checkDeadlineInput(String input, int state) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            uiInstance.Warning("Description for a deadline cannot be empty");
        } else if (state == 0) {
            uiInstance.Warning("There is no date for a deadline");
        } else if (state > 1) {
            uiInstance.Warning("Too many /by statements");
        }
    }

    /**
     * Checks the validity of Event input.
     * Throws a DukeException if the description or date range is incomplete.
     *
     * @param input The description of the Event task.
     * @param state The state indicating whether the "/from" and "/to" dates were provided correctly.
     * @throws DukeException If the input is invalid or incomplete.
     */
    public void checkEventInput(String input, int state) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            uiInstance.Warning("Description for an event cannot be empty");
        } else if (state == 0) {
            uiInstance.Warning("There is no start and end for this event");
        } else if (state == 1) {
            uiInstance.Warning("There is no end for this event");
        }
    }

    /**
     * Checks the validity of index input for marking and unmarking tasks.
     * Throws a DukeException if the index is invalid or out of range.
     *
     * @param index The index of the task to be marked/unmarked.
     * @param taskCount The total number of tasks in the list.
     * @throws DukeException If the index is out of bounds.
     */
    public void checkMarkUnmarkInput(int index, int taskCount) throws DukeException {
        if (index < 0 || index > taskCount) {
            uiInstance.Warning("You have input an invalid index");
        }
    }

    /**
     * Handles general invalid input commands.
     * Throws a DukeException if the command is not recognized.
     *
     * @throws DukeException If the command is invalid.
     */
    public void checkGeneralInput() throws DukeException {
        uiInstance.Warning("Sorry I cannot understand that");
    }

    /**
     * Processes the user's input commands.
     * Executes appropriate actions based on the command (e.g., adding tasks, marking tasks as done).
     *
     * @param input The user's input command as a string.
     * @return -1 if the "bye" command is received, 0 for other commands.
     * @throws DukeException If an error occurs during command processing.
     */
    public int processCommand(String input) throws DukeException {
        String[] inputComponent = input.split(" ");

        // Switch case based on the first word of input
        try {
            switch (inputComponent[0]) {
                case "bye":
                    uiInstance.displayMessage("Bye. Hope to see you again soon!");
                    return -1;
                case "list":
                    taskListInstance.list();
                    break;
                case "mark":
                    try {
                        TaskList.markAsDone(Integer.parseInt(inputComponent[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        uiInstance.displayMessage("Warning: You haven't input any number");
                    }
                    break;
                case "unmark":
                    try {
                        taskListInstance.markAsNotDone(Integer.parseInt(inputComponent[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        uiInstance.displayMessage("Warning: You haven't input any number");
                    }
                    break;
                case "todo":
                    taskListInstance.addToDo(inputComponent);
                    break;
                case "deadline":
                    taskListInstance.addDeadline(inputComponent);
                    break;
                case "event":
                    taskListInstance.addEvent(inputComponent);
                    break;
                case "delete":
                    taskListInstance.deleteTask(Integer.parseInt(inputComponent[1]));
                    break;
                case "find":
                    taskListInstance.findTaskKeyword(inputComponent);
                    break;
                default:
                    checkGeneralInput(); // Check for invalid command
            }
        } catch (DukeException e) {
            e.displayMessage();
        }
        return 0;
    }
}
