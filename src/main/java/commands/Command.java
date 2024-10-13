package commands;

import task.TaskList;
import UI.Ui;
import storage.Storage;
import exception.LiaException;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks to manage.
     * @param ui The user interface for interactions.
     * @param storage The storage for saving and loading tasks.
     * @throws LiaException if an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LiaException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false; // Default implementation; can be overridden.
    }
}
