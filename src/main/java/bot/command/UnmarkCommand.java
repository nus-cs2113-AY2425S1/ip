package bot.command;

import bot.Storage;
import bot.Ui;
import task.TaskList;

/**
 * Command to unmark a task (mark it as not done) based on its index.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index The index of the task to be unmarked (1-based index).
     */
    public UnmarkCommand(int index) {
        this.index = index - 1; // Convert to 0-based index
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done.
     *
     * @param tasks   The list of tasks to unmark a task.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(index).markAsNotDone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Invalid task number.");
        }
    }
}