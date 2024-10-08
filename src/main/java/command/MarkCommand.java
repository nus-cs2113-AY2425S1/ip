package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import exception.InvalidTaskNumberException;

/**
 * Represents command used to mark task as done in task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Construct MarkCommand with defined task index.
     * @param taskIndex index of task to be marked as complete
     * @throws InvalidTaskNumberException if task index is not a valid integer
     */
    public MarkCommand(String taskIndex) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

    /**
     * Abstract method used to execute MarkCommand to mark defined task as done.
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     * @throws Exception if task index is invalid or issue occurred while saving
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
            tasks.getTask(taskIndex).markTaskDone();
            ui.showMessage("Marked this task as done:\n" + tasks.getTask(taskIndex));
            storage.saveTasksToFile(tasks.getTasks());
        } else {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}
