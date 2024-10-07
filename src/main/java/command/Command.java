package command;

import exception.InvalidCommandException;
import exception.UserInputException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, UI, and storage.
     *
     * @param tasks   the task list to manipulate
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws InvalidCommandException if the command is invalid
     * @throws UserInputException if there is an error with user input
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, UserInputException;

    public boolean isExit() {
        return false;
    }
}
