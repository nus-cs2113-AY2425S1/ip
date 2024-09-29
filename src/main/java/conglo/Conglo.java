package conglo;

import conglo.exception.StorageInvalidFormat;
import conglo.ui.Ui;
import conglo.exception.CongloException;
import conglo.storage.Storage;
import conglo.task.TaskList;
import conglo.command.Parser;

import java.io.FileNotFoundException;

/**
 * Main class for the Conglo chatbot.
 * Initializes the application, loads tasks, and handles user interactions.
 */
public class Conglo {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Conglo instance with the specified file path for task storage.
     * Loads existing tasks or initializes a new task list if loading fails.
     *
     * @param filePath the file path to load tasks from and save tasks to
     */
    public Conglo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | StorageInvalidFormat e) {
            ui.displayLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, processing user input until the 'bye' command is given.
     */
    public void run() {
        ui.greetUser();
        String input;
        boolean isQuit = false;

        while (!isQuit) {
            input = ui.getUserInput();
            if (input.equals("bye")) {
                isQuit = true;
                ui.sayGoodbye();
            }
            try {
                Parser.processCommand(taskList, input);
            } catch (CongloException e) {
                System.out.println(e.getMessage());
            }
            ui.printLineSeparator();
        }
        ui.closeScanner();
    }

    /**
     * The main entry point of the Conglo application.
     * Initializes the application and starts the main run loop.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Conglo("./data/conglo.txt").run();
    }
}
