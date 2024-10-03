package tars.command;

import tars.userinterface.UserInterface;
import tars.tasklist.TaskList;
import tars.storage.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list to the user.
     *
     * @param tasks   The task list containing all the tasks to be displayed.
     * @param ui      The user interface to display the tasks.
     * @param storage The storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }
}
