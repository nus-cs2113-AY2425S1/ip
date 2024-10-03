/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;
    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the specified task from the task list.
     *
     * @param tasks  The task list from which the task will be deleted.
     * @param ui     The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file.
     * @throws PoirotException If the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PoirotException {
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new PoirotException("Task number is out of bounds!");
        }
        Task taskToDelete = tasks.getTask(index - 1);
        tasks.delete(index - 1);
        ui.showDeletedTask(taskToDelete, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
