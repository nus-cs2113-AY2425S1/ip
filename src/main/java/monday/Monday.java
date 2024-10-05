package monday;

import command.*;
import exception.MondayException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The main class for the Monday task management application.
 */
public class Monday {
    private static final String FILE_PATH = "./data/monday.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the Monday application, setting up UI, storage, and loading tasks.
     */
    public Monday() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (MondayException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application, displaying the welcome message and handling user input.
     */
    public void run() {
        ui.printWelcomeMessage();
        handleUserInput();
    }

    /**
     * Handles user input in a loop, parsing commands and executing them.
     */
    private void handleUserInput() {
        String input;
        while (true) {
            input = ui.getInput();
            ui.printLine();

            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);

                // After executing the command, save the tasks
                storage.save(tasks.getTasks());

                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (MondayException e) {
                ui.showError(e.getMessage());
            }

            ui.printLine();
        }
    }

    /**
     * Main method to start the Monday application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Monday().run();
    }

}
