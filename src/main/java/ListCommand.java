/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to display the list of tasks.
     *
     * @param tasks  The task list to display.
     * @param ui     The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());
    }
}
