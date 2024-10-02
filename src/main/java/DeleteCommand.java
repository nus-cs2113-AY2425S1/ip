/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to delete.
     * @throws BebeException if the index is not a valid number.
     */
    public DeleteCommand(String index) throws BebeException {
        try {
            this.taskIndex = Integer.parseInt(index) - 1; // Convert 1-based index to 0-based.
        } catch (NumberFormatException e) {
            throw new BebeException("Invalid task number format.");
        }
    }

    /**
     * Executes the delete command by removing a task from the task list.
     *
     * @param tasks   The task list where the task will be deleted.
     * @param ui      The UI object to display results to the user.
     * @param storage The storage object to save the updated task list.
     * @throws BebeException if the task index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        try {
            Task taskToRemove = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.show("Deleted task: " + taskToRemove);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task does not exist.");
        }
    }
}