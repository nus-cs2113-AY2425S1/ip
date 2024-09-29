package aether;

import aether.command.Command;
import aether.parser.Parser;
import aether.storage.Storage;
import aether.task.Task;
import aether.tasklist.TaskList;
import aether.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class for Aether task manager.
 */
public class Aether {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private boolean isExit = false;

    public static void main(String[] args) {
        new Aether("data/aether.txt").run();
    }

    public Aether(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> tasks = storage.load();
            taskList = new TaskList(tasks);
        } catch (IOException e) {
            Ui.response("Error loading tasks: " + e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        Ui.showStartScreen();

        while (!isExit) {
            String commandInput = Ui.getInput();
            Ui.printSeparator();
            try {
                // Ensure that the command is parsed and executed only once
                Command command = Parser.parseCommand(commandInput);
                command.execute(taskList, ui, storage);  // Only execute the command once
                isExit = command.isExit();  // Check if this is an exit command
            } catch (DukeException e) {
                Ui.response(e.getMessage());
            }
            Ui.printSeparator();
        }

        Ui.showEndScreen();
    }
}
