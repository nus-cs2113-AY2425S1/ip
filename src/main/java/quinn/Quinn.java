package quinn;

import quinn.command.Command;
import quinn.exception.QuinnException;
import quinn.parser.Parser;
import quinn.task.TaskList;
import quinn.ui.Ui;
import quinn.storage.Storage;

import java.io.IOException;

public class Quinn {
    private final Ui ui;
    private final Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Quinn(String folderName, String fileName) {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(folderName, fileName);
            taskList = storage.loadTasksFromFile();
        } catch (QuinnException | IOException e) {
            ui.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Quinn("data", "tasks.txt").run();
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                Command command = parser.parse(commandLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (QuinnException | IOException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }
}
