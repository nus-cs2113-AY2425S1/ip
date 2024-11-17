package aegis.command;

import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

/**
 * The ListCommand class handles the display of all tasks in the task list.
 * It allows the user to view the current tasks in the list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying all the tasks in the task list to the user.
     *
     * @param tasks   The TaskList containing the tasks to be displayed.
     * @param ui      The Ui used for interacting with the user.
     * @param storage The Storage used for task persistence (not affected by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayTasks();
    }
}
