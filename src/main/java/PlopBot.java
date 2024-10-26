import java.util.ArrayList;

/**
 * Main class for the PlopBot task management application.
 * Handles the core functionality of managing tasks, processing commands,
 * and coordinating between different components (UI, Storage, Parser).
 */
public class PlopBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Main entry point for the PlopBot application.
     * Initializes and runs the bot with default storage location.
     *
     * @param args - Command line arguments (not used)
     */
    public static void main(String[] args) {
        new PlopBot("data/tasks.txt").run();
    }

    /**
     * Constructs a new PlopBot instance with specified storage location.
     * Initializes UI, storage, task list, and parser components.
     *
     * @param filePath - Path to the file where tasks will be stored
     */
    public PlopBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PlopBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    /**
     * Starts the main execution loop of PlopBot.
     * Continuously reads and processes user commands until exit is requested.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                isExit = processCommand(fullCommand);
            } catch (PlopBotException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }

    /**
     * Processes a single command from the user's input.
     *
     * @param fullCommand       - The full command string input by the user
     * @return                  - Boolean indicating if the program should exit
     * @throws PlopBotException - If the command is invalid or execution fails
     */
    private boolean processCommand(String fullCommand) throws PlopBotException {
        String[] commandParts = parser.parseCommand(fullCommand);
        switch (commandParts[0]) {
        case "bye":
        case "exit":
        case "quit":
            return true;
        case "todo":
            handleAddTask(commandParts);
            break;
        case "deadline":
            handleAddDeadline(commandParts);
            break;
        case "event":
            handleAddEvent(commandParts);
            break;
        case "delete":
            handleDeleteTask(commandParts);
            break;
        case "find":
            handleFindTasks(commandParts);
            break;
        case "list":
            handleListTasks();
            break;
        case "mark":
            handleMarkTask(commandParts);
            break;
        case "unmark":
            handleUnmarkTask(commandParts);
            break;
        default:
            throw new PlopBotException("Unknown command");
        }
        return false;
    }

    /**
     * Common method to add a task and save to storage.
     *
     * @param task              - The task to add
     * @throws PlopBotException - If task creation or saving fails
     */
    private void addAndSaveTask(Task task) throws PlopBotException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Handles adding a new task and saving it.
     *
     * @param commandParts      - The parsed command parts
     * @throws PlopBotException - If task creation or saving fails
     */
    private void handleAddTask(String[] commandParts) throws PlopBotException {
        Task newTask = parser.parseTask(commandParts);
        addAndSaveTask(newTask);
    }

    /**
     * Handles adding a deadline task with proper error handling.
     *
     * @param commandParts      - The parsed command parts
     * @throws PlopBotException - If deadline creation or saving fails
     */
    private void handleAddDeadline(String[] commandParts) throws PlopBotException {
        try {
            Task newDeadline = parser.parseTask(commandParts);
            addAndSaveTask(newDeadline);
        } catch (PlopBotException e) {
            throw new PlopBotException(formatDeadlineError(e.getMessage()));
        }
    }

    /**
     * Formats the deadline error message.
     *
     * @param baseMessage - The base error message
     * @return            - Formatted error message
     */
    private String formatDeadlineError(String baseMessage) {
        return String.format("%s\n    Usage: deadline description /by DATE" +
                        "\n    DATE can be 'Sunday', 'Mon', 'Tuesday', or 'YYYY-MM-DD'",
                baseMessage);
    }

    /**
     * Handles adding an event task with proper error handling.
     *
     * @param commandParts      - The parsed command parts
     * @throws PlopBotException - If event creation or saving fails
     */
    private void handleAddEvent(String[] commandParts) throws PlopBotException {
        try {
            Task newEvent = parser.parseTask(commandParts);
            addAndSaveTask(newEvent);
        } catch (PlopBotException e) {
            throw new PlopBotException(formatEventError(e.getMessage()));
        }
    }

    /**
     * Formats the event error message.
     *
     * @param baseMessage - The base error message
     * @return            - Formatted error message
     */
    private String formatEventError(String baseMessage) {
        return String.format("%s\n    Usage: event description /from START_TIME /to END_TIME" +
                        "\n    TIME can be 'Mon 2pm', 'Tuesday 14:00', or 'YYYY-MM-DD HH:MM'",
                baseMessage);
    }

    /**
     * Handles deleting a task.
     *t
     * @param commandParts      - The parsed command parts
     * @throws PlopBotException - If the command format is invalid or task deletion fails
     */
    private void handleDeleteTask(String[] commandParts) throws PlopBotException {
        if (commandParts.length != 2) {
            throw new PlopBotException("Invalid delete command. Usage: delete <task number>");
        }
        try {
            int index = parseTaskIndex(commandParts[1]);
            if (index < 0 || index >= tasks.size()) {
                throw new PlopBotException("Task number " + (index + 1) + " does not exist. Please use 'list' to see all tasks.");
            }
            Task removedTask = tasks.removeTask(index);
            storage.save(tasks.getTasks());
            ui.showTaskRemoved(removedTask, tasks.size());
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid task number. Please provide a number.");
        }
    }

    /**
     * Handles finding tasks by keyword.
     *
     * @param commandParts      - The parsed command parts
     * @throws PlopBotException - If the find command format is invalid
     */
    private void handleFindTasks(String[] commandParts) throws PlopBotException {
        validateFindCommand(commandParts);
        String keyword = commandParts[1];
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Validates the find command format.
     *
     * @param commandParts      - The parsed command parts
     * @throws PlopBotException - If the command format is invalid
     */
    private void validateFindCommand(String[] commandParts) throws PlopBotException {
        if (commandParts.length < 2) {
            throw new PlopBotException("The 'find' command requires a keyword.\n    Usage: find <keyword>");
        }
    }

    /**
     * Handles listing all tasks.
     */
    private void handleListTasks() {
        ui.showTasks(tasks.getTasks());
    }

    /**
     * Parses a task index from string input.
     *
     * @param indexStr          - The index string to parse
     * @return                  - The parsed index (0-based)
     * @throws PlopBotException - If the index string is not a valid number
     */
    private int parseTaskIndex(String indexStr) throws PlopBotException {
        try {
            return Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid task number. Please provide a number.");
        }
    }

    /**
     * Handles marking a task as done.
     *
     * @param commandParts      - The parsed command parts containing the task index
     * @throws PlopBotException - If the command format is invalid or task marking fails
     */
    private void handleMarkTask(String[] commandParts) throws PlopBotException {
        if (commandParts.length != 2) {
            throw new PlopBotException("Invalid mark command. Usage: mark <task number>");
        }
        try {
            int taskIndex = Integer.parseInt(commandParts[1]) - 1;
            Task task = tasks.getTask(taskIndex);
            task.markAsDone();
            storage.save(tasks.getTasks());
            ui.showTaskMarked(task);
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid task number. Please provide a number.");
        }
    }

    /**
     * Handles unmarking a task (setting it as not done).
     *
     * @param commandParts      - The parsed command parts containing the task index
     * @throws PlopBotException - If the command format is invalid or task unmarking fails
     */
    private void handleUnmarkTask(String[] commandParts) throws PlopBotException {
        if (commandParts.length != 2) {
            throw new PlopBotException("Invalid unmark command. Usage: unmark <task number>");
        }
        try {
            int taskIndex = Integer.parseInt(commandParts[1]) - 1;
            Task task = tasks.getTask(taskIndex);
            task.markAsUndone();
            storage.save(tasks.getTasks());
            ui.showTaskUnmarked(task);
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid task number. Please provide a number.");
        }
    }
}
