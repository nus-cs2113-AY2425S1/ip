package command;

import exception.InvalidCommandException;
import exception.UserInputException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

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
     * @throws UserInputException,InvalidCommandException if there is an error during execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UserInputException, InvalidCommandException {
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
