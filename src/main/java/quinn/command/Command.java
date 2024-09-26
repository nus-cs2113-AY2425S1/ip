package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command in the task management system.
 * This interface defines the basic structure for all commands.
 */
public interface Command {
    /**
     * Checks if this command should cause the program to exit.
     *
     * @return true if this command should cause the program to exit, false otherwise
     */
    boolean isExit();

    /**
     * Executes the command.
     *
     * @param taskList the list of tasks to operate on
     * @param ui the user interface to display results
     * @param storage the storage to save updates
     * @throws QuinnException if there's an error specific to the application
     * @throws IOException if there's an I/O error during execution
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException, IOException;
}
