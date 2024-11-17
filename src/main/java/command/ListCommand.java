package command;

import data.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The ListCommand class handles the listing of all tasks in the task list.
 * It retrieves and displays tasks currently stored, or informs the user if the list is empty.
 *
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks in the task list.
     * If the task list is empty, it prints a message indicating that no tasks have been added.
     *
     * @param tasks The task list containing all tasks to be displayed.
     * @param storage The storage (not used in this command but included for method signature).
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            Ui.printEmptyListMessage();
            return;
        }
        Ui.printNonEmptyListMessage();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1) + "." + currentTask);
        }
    }
}
