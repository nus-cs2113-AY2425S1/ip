/**
 * Represents a command to unmark a task as not completed in the Poirot task management system.
 * This command changes the status of a task from completed to not completed.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a new UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked (1-based indexing).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command.
     * This method unmarks the specified task as not completed, updates the UI,
     * and saves the changes to storage.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object used to display messages to the user.
     * @param storage The Storage object used to save tasks.
     * @throws PoirotException If the task index is out of bounds.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PoirotException {
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new PoirotException("Task number is out of bounds!");
        }
        tasks.markTask(index - 1, false);
        ui.showUnmarkTask(tasks.getTask(index - 1));
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
