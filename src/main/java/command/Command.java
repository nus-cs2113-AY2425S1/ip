package command;

import model.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.MondayException;

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
     * @throws MondayException if there is an error during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException;

    public boolean isExit() {
        return false;
    }
}
