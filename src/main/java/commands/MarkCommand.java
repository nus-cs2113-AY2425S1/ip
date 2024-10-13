package commands;

import task.TaskList;
import task.Task;
import UI.Ui;
import storage.Storage;
import exception.LiaException;

/**
 * Command to mark or unmark a task.
 */
public class MarkCommand extends Command {
    private final String taskNumberStr;
    private final boolean markDone;

    /**
     * Constructs a MarkCommand with the specified task number string and mark status.
     *
     * @param taskNumberStr The task number of the task to be marked/unmarked.
     * @param markDone True to mark the task as done, false to unmark it.
     */
    public MarkCommand(String taskNumberStr, boolean markDone) {
        this.taskNumberStr = taskNumberStr;
        this.markDone = markDone;
    }

    /**
     * Executes the command to mark or unmark a task.
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

        Task task = tasks.get(taskIndex);
        if (markDone) {
            task.markAsDone();
            ui.showMarkDone(task);
        } else {
            task.markAsNotDone();
            ui.showUnmarkDone(task);
        }
    }
}
