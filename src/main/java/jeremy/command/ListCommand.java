package jeremy.command;

import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by displaying the task list.
     *
     * @param tasks   The task list to be printed.
     * @param ui      The user interface that displays the task list.
     * @param storage The storage system for saving/loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
