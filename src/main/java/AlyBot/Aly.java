package AlyBot;

import Commands.Command;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the main class for the AlyBot application.
 * This class manages the interaction between the user, task list, and storage.
 */
public class Aly {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final Path SAVE_FILE = Paths.get("data", "taskdata.txt");

    /**
     * Constructs an Aly instance using a specified file path.
     * Initializes the UI, storage, and loads existing tasks if available.
     *
     * @param filePath The path to the storage file for saving tasks.
     */
    public Aly(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (AlyException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Starts the AlyBot application, handling user commands and executing tasks
     * until the exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        try {
            storage.createDirectory();
            File file = new File(SAVE_FILE.toString());
            storage.createFile(file);
        } catch (AlyException e) {
            ui.showError(e.getMessage());
        }
        ui.printLine();
        ui.startingMessage();
        ui.printLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.hasExited();
            } catch (AlyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * The entry point for the AlyBot application.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Aly(SAVE_FILE).run();
    }
}