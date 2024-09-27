import java.util.Scanner;

import tyrone.constants.Constants;
import tyrone.exceptions.*;
import tyrone.parser.Parser;
import tyrone.storage.Storage;
import tyrone.task.*;
import tyrone.tasklist.TaskList;
import tyrone.ui.Ui;

public class Tyrone {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Tyrone(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());  // Now loadTasks() returns a list of tasks
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = Parser.handleUserInput(fullCommand, tasks);
            } catch (TyroneException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Tyrone("data/tasks.txt").run();
    }
}