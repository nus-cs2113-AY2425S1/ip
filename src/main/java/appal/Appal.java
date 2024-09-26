package appal;

import appal.commands.Command;
import appal.exception.AppalException;
import appal.exception.NoSavedTasksException;
import appal.parser.Parser;
import appal.task.TaskList;
import appal.ui.Ui;
import appal.storage.*;

public class Appal {
    // Attributes
    private final Ui ui;
    private boolean isExit = false;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    public Appal() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage();
    }

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
