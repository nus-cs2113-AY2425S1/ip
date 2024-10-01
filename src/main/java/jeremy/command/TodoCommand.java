package jeremy.command;

import jeremy.exception.EmptyArgumentException;
import jeremy.task.Todo;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command {
    private final String argument;

    /**
     * Constructs a TodoCommand with the specified argument.
     *
     * @param argument The description for the todo task.
     */
    public TodoCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes the command by adding a todo task to the task list.
     *
     * @param tasks   The task list to be manipulated by the command.
     * @param ui      The user interface that displays feedback to the user.
     * @param storage The storage system for saving/loading tasks.
     * @throws EmptyArgumentException if the todo description is missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyArgumentException {
        tasks.addTask(new Todo(argument));
    }
}
