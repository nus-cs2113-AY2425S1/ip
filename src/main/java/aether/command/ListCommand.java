package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;

/**
 * Handles the "list" command to display all tasks.
 * <p>
 * This command retrieves all tasks from the task list and displays them to the user.
 * If the task list is empty, it notifies the user accordingly.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by retrieving and displaying all tasks.
     *
     * @param taskList The {@code TaskList} object containing all current tasks.
     * @param ui       The {@code Ui} object responsible for user interactions.
     * @param storage  The {@code Storage} object responsible for saving and loading tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
    }
}
