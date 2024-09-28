package bosco;

import bosco.ui.Ui;
import bosco.task.TaskList;
import bosco.parser.Parser;
import bosco.storage.Storage;

import bosco.command.Command;
import bosco.command.ExitCommand;

import bosco.exception.EmptyDescriptionException;
import bosco.exception.EmptyKeywordException;
import bosco.exception.IllegalCommandException;
import bosco.exception.IllegalDateTimeException;
import bosco.exception.MissingPrefixException;

import java.io.IOException;

/**
 * The Bosco program implements a chatbot application that
 * interacts with the user and stores tasks.
 */
public class Bosco {
    private static final String FILE_PATH = "./data/bosco.txt";

    private final Ui ui;
    private final Parser parser;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Class constructor.
     *
     * @param filePath File path of data file storing tasks.
     */
    public Bosco(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
    }

    /**
     * Represents the entry point of this program.
     */
    public static void main(String[] args) {
        new Bosco(FILE_PATH).run();
    }

    private void run() {
        startProgram();
        runCommandLoopUntilExit();
        exitProgram();
    }

    private void startProgram() {
        try {
            tasks = new TaskList(storage.loadFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printWelcomeMessage();
    }

    private void exitProgram() {
        ui.printExitMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilExit() {
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
            } catch (EmptyKeywordException e) {
                ui.printMessages("Error: keyword is empty. Please provide a keyword!");
            } catch (MissingPrefixException e) {
                ui.printMessages("Error: missing " + e.missingPrefix + " prefix.");
            } catch (IllegalDateTimeException e) {
                ui.printMessages("Error: invalid datetime format.");
            }

            try {
                storage.writeToFile(tasks.getAllTasks());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
