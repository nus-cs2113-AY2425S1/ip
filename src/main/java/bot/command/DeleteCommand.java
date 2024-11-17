package bot.command;

import bot.Storage;
import bot.TobyBotException;
import bot.Ui;
import task.TaskList;

/**
 * Command to delete a task from the task list based on its index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index The index of the task to be deleted (1-based index).
     */
    public DeleteCommand(int index) {
        this.index = index - 1; // Convert to 0-based index
    }

    /**
     * Executes the delete command by removing the task at the specified index.
     *
     * @param tasks   The list of tasks from which a task is removed.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated tasks.
     * @throws TobyBotException If the index is out of bounds or the task cannot be removed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException {
        try {
            ui.showMessage("Noted. I've removed this task:\n  " + tasks.removeTask(index));
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new TobyBotException("Invalid task number.");
        }
    }
}