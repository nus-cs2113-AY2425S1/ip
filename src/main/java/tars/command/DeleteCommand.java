package tars.command;

import tars.userinterface.UserInterface;
import tars.storage.Storage;
import tars.tasklist.TaskList;
import tars.task.Task;
import tars.tarsexception.tarsException;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete a task from the task list, display it in the UI, and save the updated task list to storage.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface to display task information.
     * @param storage The storage to save the updated task list.
     * @throws tarsException If the task cannot be found or if an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws tarsException {
        Task task = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(task, tasks.getTaskCount());

        // Handle possible IOException during task saving
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
