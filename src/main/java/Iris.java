import data.Storage;
import exceptions.IrisException;
import task.TaskList;
import ui.Ui;
import command.Parser;
import command.Command;

/**
 * The Iris class is the main entry point for the Iris program.
 * It manages tasks by reading user commands and saving them to a file
 * before closing the application.
 *
 * @author Tan Ping Hui
 */

public class Iris {
    private static final String STORAGE_FILE_PATH = "./iris.txt";

    private static Storage storage;
    private final TaskList tasks;
    private static Ui ui;

    /**
     * Constructs an Iris object to handle task management.
     * Initializes the user interface (Ui) and loads the task list from the storage file.
     *
     * @param filePath The path to the text file where tasks are stored.
     */
    public Iris(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the main event loop for the Iris program.
     * Continuously reads user commands, executes them, and determines whether to exit.
     */
    private void run() {
        Ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (IrisException e) {
                Ui.showErrorMessage(e.getMessage());
            } finally {
                Ui.showDivider();
            }
        }
    }

    /**
     * The main method that serves as the entry point to the Iris program.
     * It initializes the Iris object and starts the main event loop.
     *
     * @param args Command-line arguments (not used in this version).
     */
    public static void main(String[] args) {
        new Iris(STORAGE_FILE_PATH).run();
    }
}
