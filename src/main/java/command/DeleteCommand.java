package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import exception.InvalidTaskNumberException;

/**
 * Command to handle deletion of a task from task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Construct DeleteCommand with defined task index
     * @param taskIndex index of task to be deleted
     * @throws InvalidTaskNumberException if task index is not a valid integer
     */
    public DeleteCommand(String taskIndex) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

    /**
     * Abstract method to execute DeleteCommand to remove task from task list.
     * @param tasks task taken from task list to be deleted
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     * @throws Exception if task index is invalid or when faced with issue saving records
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }

        tasks.deleteTask(taskIndex);
        ui.showMessage("Removed task number: " + (taskIndex + 1));
        storage.saveTasksToFile(tasks.getTasks());
    }
}