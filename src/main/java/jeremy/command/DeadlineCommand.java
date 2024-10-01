package jeremy.command;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;
import jeremy.task.Deadline;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final String argument;

    /**
     * Constructs a DeadlineCommand with the specified argument.
     *
     * @param argument The description and due date for the deadline task.
     */
    public DeadlineCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes the command by adding a deadline task to the task list.
     *
     * @param tasks   The task list to be manipulated by the command.
     * @param ui      The user interface that displays feedback to the user.
     * @param storage The storage system for saving/loading tasks.
     * @throws EmptyArgumentException if the deadline argument is missing.
     * @throws InvalidCommandFormatException if the deadline format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyArgumentException, InvalidCommandFormatException {
        tasks.addTask(new Deadline(argument));
    }
}
