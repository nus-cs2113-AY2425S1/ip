package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

import Ryan.tasks.Task;

import Ryan.exceptions.RyanException;

/**
 * Command to unmark a task as completed.
 */

public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The task number to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (!isValidIndex(index, tasks.size())) {
            throw new RyanException("Invalid task number.");
        }

        Task task = tasks.getTask(index);
        task.unmark();
        ui.showTaskUnmarked(task);
    }

    /**
     * Checks if the given index is valid.
     *
     * @param index The index to check.
     * @param size The size of the task list.
     * @return True if valid, false otherwise.
     */
    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
