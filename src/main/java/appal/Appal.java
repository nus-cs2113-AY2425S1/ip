package appal;

import appal.commands.Command;
import appal.exception.AppalException;
import appal.exception.NoSavedTasksException;
import appal.parser.Parser;
import appal.task.TaskList;
import appal.ui.Ui;
import appal.storage.Storage;

/**
 * Appal class contains functions to initialise Appal.
 */
public class Appal {
    // Attributes of Appal
    private final Ui ui;
    private boolean isExit = false;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Class constructor.
     */
    public Appal() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage();
    }

    /**
     * Runs the Appal chatbot application.
     * Starts by welcoming user and loading previously saved tasks (if applicable),
     * then loops the main program to take in user input until exit flag is set.
     */
    public void runAppal() {
        ui.welcomeUser();
        try {
            storage.loadExistingTasksData(taskList, ui);
        } catch (NoSavedTasksException e) {
            ui.printMessage(e.getMessage());
        }
        while (!isExit) {
            try {
                String line = ui.getInput();
                String[] inputDetails = parser.extractInputDetails(line);
                Command c = parser.extractCommand(inputDetails, true);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (AppalException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }
}
