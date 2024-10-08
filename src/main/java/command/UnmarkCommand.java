package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import exception.InvalidTaskNumberException;

/**
 * Represent command to unmark task as not done in task list.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Construct UnmarkCommand with defined task index
     * @param taskIndex index of task to unmark as not done
     * @throws InvalidTaskNumberException if task index is not valid integer
     */
    public UnmarkCommand(String taskIndex) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1; 
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

    /**
     * Abstract method used to execute UnmarkCommand by marking defined task as not done.
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     * @throws Exception if task index is invalid or issue occurred during saving
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
            tasks.getTask(taskIndex).unmarkTask();
            ui.showMessage("Marked this task as not done:\n" + tasks.getTask(taskIndex));
            storage.saveTasksToFile(tasks.getTasks());  // Save after unmarking task
        } else {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}
