package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.LIST_COMMAND;

/**
 * Represents a command that lists all tasks in Bento.
 * This class extends {@link Command} and handles the execution of the list command,
 * which displays the current tasks to the user.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand, initializing it with the list command prefix.
     */
    public ListCommand() {
        super(LIST_COMMAND);
    }

    /**
     * Executes the list command, displaying the current tasks to the user.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
