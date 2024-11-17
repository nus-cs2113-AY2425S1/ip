package aegis;

import aegis.command.*;

/**
 * The Aegis class is the main entry point of the Aegis application.
 * It initializes the application components including storage, task list, and user interface,
 * and handles the main program loop for interacting with the user.
 */
public class Aegis {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an Aegis object with the specified file path for data storage.
     * Initializes the storage, task list, and user interface.
     * If loading the task list from storage fails, it initializes an empty task list.
     *
     * @param filePath The file path to load and save the task list data.
     */
    public Aegis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (AegisException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Aegis application.
     * It displays a welcome message, reads user commands, and processes them until the exit command is given.
     * Handles exceptions during command execution and provides feedback to the user.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (AegisException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method that serves as the entry point of the application.
     * Initializes the Aegis application with the specified file path and starts the run loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Aegis("./data/duke.txt").run();
    }
}
