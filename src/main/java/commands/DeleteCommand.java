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

    public DeleteCommand(String taskNumberStr) {
        this.taskNumberStr = taskNumberStr;
    }

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

