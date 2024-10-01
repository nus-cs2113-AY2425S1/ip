package jeremy.command;

import jeremy.exception.InvalidTaskNumberException;
import jeremy.exception.TaskNotFoundException;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final String argument;

    /**
     * Constructs a MarkCommand with the specified argument.
     *
     * @param argument The task number of the task to be marked as done.
     */
    public MarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes the command by marking a task as done in the task list.
     *
     * @param tasks   The task list to be manipulated by the command.
     * @param ui      The user interface that displays feedback to the user.
     * @param storage The storage system for saving/loading tasks.
     * @throws TaskNotFoundException if the task specified by the argument does not exist.
     * @throws InvalidTaskNumberException if the task number provided is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException, InvalidTaskNumberException {
        tasks.markTaskAsDone(argument);
    }
}
