package medea;

import medea.command.Command;
import medea.core.Parser;
import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.exceptions.MedeaException;

/**
 * The Medea class serves as the main application interface,
 * managing the flow of the application, including command processing,
 * task loading, and saving tasks to storage.
 */
public class Medea {
    private static final String DEFAULT_FILE_PATH = "./data/medea.txt";
    private final Ui userInterface;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser commandParser;

    /**
     * Constructs a Medea instance with the specified file path for storage.
     * Initializes the user interface, storage, command parser, and loads tasks.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Medea(String filePath) {
        userInterface = new Ui();
        storage = new Storage(filePath);
        commandParser = new Parser();
        taskList = loadTasks();
    }

    /**
     * Loads tasks from storage and returns a TaskList.
     * If loading fails, handles the error and returns an empty TaskList.
     *
     * @return a TaskList containing loaded tasks
     */
    private TaskList loadTasks() {
        try {
            return new TaskList(storage.loadTasks());
        } catch (MedeaException e) {
            handleError(e);
            return new TaskList(); // Return an empty TaskList if loading fails
        }
    }

    /**
     * The main method to start the application.
     * Creates an instance of Medea with the default file path and calls run().
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Medea(DEFAULT_FILE_PATH).run();
    }

    /**
     * Runs the main application loop, showing welcome and farewell messages,
     * and processing user commands.
     */
    public void run() {
        userInterface.showWelcome();
        handleUserCommands();
        userInterface.showFarewell();
        saveTasks();
    }

    /**
     * Continuously reads and processes user commands until an exit command is issued.
     */
    private void handleUserCommands() {
        while (true) {
            try {
                String userCommand = userInterface.readCommand();
                Command command = commandParser.parse(userCommand);
                if (command.isExit()) {
                    return;
                }
                executeCommand(command);
            } catch (MedeaException exception) {
                handleError(exception);
            }
        }
    }

    /**
     * Executes the given command within line separators for better readability.
     *
     * @param command the command to execute
     */
    private void executeCommand(Command command) {
        wrapWithLine(() -> command.execute(taskList, userInterface, storage));
    }

    /**
     * Handles exceptions by wrapping error display in line separators.
     *
     * @param exception the exception to handle
     */
    private void handleError(MedeaException exception) {
        wrapWithLine(() -> userInterface.showError(exception));
    }

    /**
     * Wraps the execution of a Runnable action with line separators for better
     * output formatting.
     *
     * @param action the action to execute
     */
    private void wrapWithLine(Runnable action) {
        userInterface.showLine();
        action.run();
        userInterface.showLine();
    }

    /**
     * Saves the current tasks to storage in CSV format.
     */
    private void saveTasks() {
        storage.saveTasks(taskList.toCSVString());
    }
}
