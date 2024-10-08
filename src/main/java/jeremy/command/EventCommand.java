package jeremy.command;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;
import jeremy.task.Event;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class EventCommand extends Command {
    private final String argument;

    /**
     * Constructs an EventCommand with the specified argument.
     *
     * @param argument The description, from date, and to date for the event task.
     */
    public EventCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes the command by adding an event task to the task list.
     *
     * @param tasks   The task list to be manipulated by the command.
     * @param ui      The user interface that displays feedback to the user.
     * @param storage The storage system for saving/loading tasks.
     * @throws EmptyArgumentException if the event argument is missing.
     * @throws InvalidCommandFormatException if the event format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyArgumentException, InvalidCommandFormatException {
        tasks.addTask(new Event(argument));
    }
}
