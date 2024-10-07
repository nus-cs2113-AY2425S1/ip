package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import exception.InvalidTaskNumberException;

/**
 * Command to handle deletion of a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(String taskIndex) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

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