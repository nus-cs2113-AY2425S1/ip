package tars.command;

import tars.userinterface.UserInterface;
import tars.storage.Storage;
import tars.tasklist.TaskList;
import tars.task.Task;
import tars.tarsexception.TarsException;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not completed.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be unmarked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark a task as not done in the task list, display the update in the UI,
     * and save the updated task list to storage.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The user interface to display the task status.
     * @param storage The storage to save the updated task list.
     * @throws tarsException If the task cannot be found or an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException {
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        ui.showTaskNotDone(task);

        // Attempt to save the task list and handle possible IOException
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
