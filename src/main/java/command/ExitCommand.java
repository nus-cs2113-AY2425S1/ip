package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.MondayException; // Import the exception

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   the task list (not used in exit command)
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks (not used in exit command)
     * @throws MondayException if there is an error during execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException { // Declare it can throw MondayException
        ui.showExitMessage();
    }

    /**
     * Checks if the command indicates program exit.
     *
     * @return true since this command is for exiting the application
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
