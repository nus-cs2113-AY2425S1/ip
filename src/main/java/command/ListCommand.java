package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display all tasks in the task list.
     *
     * @param tasks   the task list to display
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks (not used in list command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
