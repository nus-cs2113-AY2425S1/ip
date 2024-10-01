package jeremy.command;

import jeremy.exception.*;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command that can be executed.
 * Each command is responsible for performing a specific action in the system.
 */
public abstract class Command {
    /**
     * Executes the command with the specified task list, user interface, and storage.
     *
     * @param tasks   The task list to be manipulated by the command.
     * @param ui      The user interface that displays feedback to the user.
     * @param storage The storage system for saving/loading tasks.
     * @throws TaskNotFoundException if the task specified by the command does not exist.
     * @throws InvalidTaskNumberException if the task number provided is invalid.
     * @throws EmptyArgumentException if the command is missing required arguments.
     * @throws InvalidCommandFormatException if the command format is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws TaskNotFoundException, InvalidTaskNumberException,
                    EmptyArgumentException, InvalidCommandFormatException;

    /**
     * Determines whether this command will exit the application.
     * Overridden only by the ByeCommand.
     *
     * @return false, as most commands do not exit the application.
     */
    public boolean isExit() {
        return false;
    }
}
