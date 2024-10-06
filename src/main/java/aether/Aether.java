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
 * Main class for the Aether task manager application.
 * <p>
 * The {@code Aether} class serves as the entry point and controller for the task manager application.
 * It initializes essential components such as user interface handling, task storage, and task management.
 * The class manages the main event loop, processing user commands until an exit command is received.
 * </p>
 */
public class Aether {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private boolean isExit = false;

    /**
     * The main method handles the starting of the Aether application.
     *
     * @param args Command line arguments. Currently unused.
     */
    public static void main(String[] args) {
        new Aether("data/aether.txt").run();
    }

    /**
     * Constructs an {@code Aether} application instance with the specified file path for task storage.
     * <p>
     * This constructor initializes the user interface, storage handler, and task list.
     * It attempts to load existing tasks from the specified storage file. If the file does not exist
     * or an error occurs during loading, it initializes an empty task list and notifies the user.
     * </p>
     *
     * @param filePath The file path for storing tasks.
     */
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

    /**
     * Starts the main event loop of the Aether application.
     * <p>
     * This method displays the start screen and enters a loop that continues to accept and process
     * user commands until an exit command is issued. For each iteration, it:
     * <ul>
     *     <li>Reads user input.</li>
     *     <li>Parses the input into a {@code Command} object.</li>
     *     <li>Executes the command, which may modify the task list or trigger application exit.</li>
     *     <li>Handles any exceptions that occur during command execution by displaying error messages.</li>
     * </ul>
     * Upon exiting the loop, it displays the end screen to the user.
     * </p>
     */
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
    }
}
