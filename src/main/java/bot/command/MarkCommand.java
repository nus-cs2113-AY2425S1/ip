package bot.command;

import bot.Storage;
import bot.Ui;
import task.TaskList;

/**
 * Command to mark a task as done based on its index.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index The index of the task to be marked as done (1-based index).
     */
    public MarkCommand(int index) {
        this.index = index - 1; // Convert to 0-based index
    }

    /**
     * Executes the mark command by marking the task at the specified index as done.
     *
     * @param tasks   The list of tasks to mark a task as done.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(index).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Invalid task number.");
        }
    }
}