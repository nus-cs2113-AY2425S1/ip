package command;

import data.Storage;
import exceptions.IrisException;
import task.TaskList;

/**
 * Abstract class representing a command in the application.
 * This class serves as a template for various specific command implementations.
 * <p>
 * Commands are executed with an associated task list and storage.
 *
 * @author Tan Ping Hui
 */
public abstract class Command {

    /**
     * Checks if the command is a termination command (ByeCommand).
     *
     * @return true if this command is an instance of ByeCommand, false otherwise.
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    /**
     * Executes the command with the given task list and storage.
     *
     * @param tasks The task list to be modified by the command.
     * @param storage The storage to save or load the task list.
     * @throws IrisException If an error occurs during command execution (e.g., invalid task).
     */
    public abstract void execute(TaskList tasks, Storage storage) throws IrisException;
}
