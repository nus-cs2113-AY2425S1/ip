package ryan.commands;

import ryan.utility.TaskList;
import ryan.utility.Ui;

import ryan.tasks.Task;

import ryan.exceptions.RyanException;
import ryan.exceptions.InvalidIndexException;

/**
 * Command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The task number to mark as completed.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (!isValidIndex(index, tasks.size())) {
            throw new InvalidIndexException();
        }

        Task task = tasks.getTask(index);
        task.mark();
        Ui.showTaskMarked(task);
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
