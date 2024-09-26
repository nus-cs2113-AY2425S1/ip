package quinn;

import quinn.command.Command;
import quinn.command.CommandType;
import quinn.command.FindCommand;
import quinn.exception.QuinnException;
import quinn.parser.Parser;
import quinn.task.TaskList;
import quinn.ui.Ui;
import quinn.storage.Storage;

import java.io.IOException;

/**
 * The Quinn class represents the main controller for the Quinn task management application.
 * It initializes the application components, manages the command execution loop, and
 * coordinates interactions between the user interface, task list, storage, and parser.
 *
 * This class is responsible for:
 *   Initializing the UI, parser, storage, and task list
 *   Running the main command execution loop
 *   Handling exceptions during command execution
 *   Managing the display of filtered tasks
 */
public class Quinn {

    /** The user interface component for handling input/output. */
    private final Ui ui;

    /** The parser for interpreting user commands. */
    private final Parser parser;

    /** The storage component for persisting task data. */
    private Storage storage;

    /** The list of tasks managed by the application. */
    private TaskList taskList;

    /**
     * Constructs a new Quinn application instance.
     * Initializes the UI, parser, storage, and task list components.
     *
     * @param folderName the name of the folder to store task data
     * @param fileName the name of the file to store task data
     */
    public Quinn(String folderName, String fileName) {
        ui = new Ui();
        parser = new Parser();

        Command command = null;

        try {
            storage = new Storage(folderName, fileName);
            taskList = storage.loadTasksFromFile();
        } catch (QuinnException | IOException e) {
            ui.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }


    /**
     * Checks if there are filtered tasks and displays them if necessary.
     * This method is called after each command execution to maintain
     * the display of filtered tasks when appropriate.
     *
     * @param command the last executed command
     */
    public void checkAndDisplayFilteredTasks(Command command) {
        if (taskList.hasFilter()) {
            String filteredTasksMessage;

            if (taskList.getFilterCommandType() == CommandType.FIND && !(command instanceof FindCommand)) {
                filteredTasksMessage = ui.tasksWithKeywordMessage(taskList, taskList.getFilterInfo());
            } else {
                filteredTasksMessage = "";
            }

            if (!filteredTasksMessage.isEmpty()) {
                ui.displayFilteredTasks(filteredTasksMessage);
            }
        }
    }

    /**
     * The main entry point of the Quinn application.
     * Creates a new Quinn instance and starts its execution.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Quinn("data", "tasks.txt").run();
    }

    /**
     * Runs the main execution loop of the Quinn application.
     * This method continuously reads user input, executes commands,
     * and displays results until an exit command is received.
     *
     * The execution loop performs the following steps:
     *   Read a command from the user
     *   Parse the command
     *   Execute the command
     *   Check and display filtered tasks if necessary
     *   Handle any exceptions that occur during execution
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;

        Command command = null;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                command = parser.parse(commandLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (QuinnException | IOException e) {
                ui.displayError(e.getMessage());
            } finally {
                checkAndDisplayFilteredTasks(command);
                ui.displayLine();
            }
        }
    }
}
