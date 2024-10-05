package monday;

import command.*;
import exception.MondayException;
import model.Task;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class Monday {
    private static final String FILE_PATH = "../data/monday.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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

    public void run() {
        ui.printWelcomeMessage();
        handleUserInput();
    }

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


    public static void main(String[] args) {
        new Monday().run();
    }
}
