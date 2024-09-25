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
            input = ui.getInput(); // Get user input
            ui.printLine();

            try {
                Command command = Parser.parse(input); // Parse the command
                command.execute(tasks, ui, storage); // Execute the command

                // After executing the command, save the tasks
                storage.save(tasks.getTasks()); // Here we call getTasks() to save the tasks

                if (command instanceof ExitCommand) {
                    break; // Exit the loop if the command is Exit
                }
            } catch (MondayException e) {
                ui.showError(e.getMessage()); // Show error if there's an exception
            }

            ui.printLine();
        }
    }


    public static void main(String[] args) {
        new Monday().run();
    }
}
