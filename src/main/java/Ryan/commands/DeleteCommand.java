package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.tasks.Task;
import Ryan.exceptions.RyanException;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param index The 1-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index - 1; // Convert to 0-based index
    }

    /**
     * Executes the task deletion command.
     *
     * @param tasks The task list to delete the task from.
     * @param ui The user interface for displaying the result.
     * @throws RyanException If the task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (!isValidIndex(index, tasks.size())) {
            throw new RyanException("Invalid task number.");
        }

        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showTaskDeleted(task, tasks.size());
    }

    /**
     * Checks if the given index is valid.
     *
     * @param index The index to check.
     * @param size The size of the task list.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
