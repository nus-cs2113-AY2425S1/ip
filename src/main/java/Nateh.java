import java.io.IOException;
import java.util.ArrayList;

import commands.Command;
import exceptions.IllegalCommandException;
import parser.Parser;
import storage.TaskDecoder;
import storage.TaskEncoder;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code Nateh} class represents the main driver for the application.
 * It handles initialization, interaction with the user, task list management,
 * and execution of commands.
 */
public class Nateh {

    /**
     * The {@code Ui} object used to handle interactions with the user.
     */
    private static Ui ui;

    /**
     * The {@code TaskList} object that stores and manages the list of tasks.
     */
    private static TaskList taskList;

    /**
     * Constructs a new {@code Nateh} object.
     * Initializes the task list by reading from a file or creating a new task list
     * if the file cannot be read.
     */
    public Nateh() {
        TaskEncoder.createFile();
        this.ui = new Ui();
        try {
            taskList = TaskDecoder.readTasks();
        } catch (IOException e) {
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the application, processing user input and executing corresponding commands.
     * The application continues running until the user enters the "bye" command.
     */
    public void run() {
        ui.printWelcomeMessage();
        String input = "";
        while (!input.equals("bye")) {
            try {
                input = ui.receiveCommand();
                Command command = Parser.parse(input);
                command.execute(taskList, ui);
            } catch (IllegalCommandException e) {
                e.runUiFunction();
            } catch (IOException e) {
                ui.printFileError();
            }
        }
    }

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Nateh().run();
    }
}
