package bosco;

import bosco.ui.Ui;
import bosco.task.TaskList;
import bosco.parser.Parser;
import bosco.storage.Storage;

import bosco.command.Command;
import bosco.command.ExitCommand;

import bosco.exception.IllegalCommandException;
import bosco.exception.EmptyDescriptionException;
import bosco.exception.MissingPrefixException;

import java.io.IOException;

public class Bosco {
    private static final String FILE_PATH = "./data/bosco.txt";

    private static Ui ui;
    private static TaskList tasks;
    private static Parser parser;
    private static Storage storage;

    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(FILE_PATH);

        try {
            tasks = new TaskList(storage.loadFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ui.printWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInputString = ui.getUserInput();
                Command command = parser.parseCommand(userInputString);
                command.setData(tasks, ui);
                command.execute();
                isExit = ExitCommand.isExit(command);
            } catch (IllegalCommandException e) {
                ui.printMessages("Error: invalid command. Please try again!");
            } catch (NumberFormatException e) {
                ui.printMessages("Error: invalid index input. Please provide a number!");
            } catch (IndexOutOfBoundsException e) {
                ui.printMessages("Error: input out of bounds. List has " + tasks.getSize() + " tasks.");
            } catch (EmptyDescriptionException e) {
                ui.printMessages("Error: task description is empty. Please provide a description!");
            } catch (MissingPrefixException e) {
                ui.printMessages("Error: missing " + e.missingPrefix + " prefix.");
            }

            try {
                storage.writeToFile(tasks.getAllTasks());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executeExitProgram();
    }

    private static void executeExitProgram() {
        ui.printExitMessage();
        System.exit(0);
    }
}
