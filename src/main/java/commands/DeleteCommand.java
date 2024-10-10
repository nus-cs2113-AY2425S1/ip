package commands;

import task.TaskList;
import task.Task;
import UI.Ui;
import storage.Storage;
import exception.LiaException;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String taskNumberStr;

    /**
     * Constructs a DeleteCommand with the specified task number string.
     *
     * @param taskNumberStr The task number of the task to be deleted.
     */
    public DeleteCommand(String taskNumberStr) {
        this.taskNumberStr = taskNumberStr;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks The list of tasks to manage.
     * @param ui The user interface for interactions.
     * @param storage The storage for saving and loading tasks.
     * @throws LiaException if the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LiaException {
        int taskIndex = Integer.parseInt(taskNumberStr) - 1;

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LiaException("Oops! Task number " + taskNumberStr + " does not exist.");
        }

        Task task = tasks.remove(taskIndex);
        ui.showDeleteTask(task, tasks.size());
    }
}
