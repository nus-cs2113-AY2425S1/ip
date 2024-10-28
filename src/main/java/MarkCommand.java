/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the specified task as done.
     *
     * @param tasks   The task list containing the task to mark.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file.
     * @throws PoirotException If the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PoirotException {
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new PoirotException("Task number is out of bounds!");
        }
        tasks.markTask(index - 1, true);
        ui.showMarkTask(tasks.getTask(index - 1));
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
