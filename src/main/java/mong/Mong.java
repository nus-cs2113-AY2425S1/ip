package mong;

import mong.exception.IllegalTaskTypeException;
import mong.storage.Storage;
import mong.task.*;
import mong.ui.Parser;
import mong.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;



public class Mong {
    public static Ui ui;
    public static TaskList taskList;
    public static Storage storage;

    /**
     * Runs the Mong program.
     * Reads user inputs and performs the respective tasks.
     */
    public static void run() {
        String input = ui.getUserInput();
        TaskType command;
        try {
            command = Parser.decipherTaskType(input.split(" ")[0]);
        } catch (IllegalTaskTypeException e) {
            command = TaskType.INVALID;
        }
        while (command != TaskType.BYE) {
            ui.printHorizontalLine();
            Parser.runCommand(command, input);
            Storage.saveToFile();
            ui.printHorizontalLine();
            input = ui.getUserInput();
            try {
                command = Parser.decipherTaskType(input.split(" ")[0]);
            } catch (IllegalTaskTypeException e) {
                command = TaskType.INVALID;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        Storage.handleFolderDoesNotExist();
        try {
            Storage.loadFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        ui.showWelcomeMessage();
        run();
        ui.showExitMessage();
    }
}
