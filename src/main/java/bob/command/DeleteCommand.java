package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

/**
 * Represents a command to delete a task from the task list.
 * Removes a task based on its index and updates the task list accordingly.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand object with the specified index of the task to be deleted.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to remove a task from the task list.
     * Displays an error message if the task list is empty or an invalid index is provided.
     *
     * @param tasks   The TaskList that the command will operate on.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to handle saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskList().isEmpty()) {
            ui.printEmptyListMessage();
            return;
        }
        try {
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showDeleteTaskMessage(removedTask, tasks.getSize());
            storage.save(tasks.getTaskList());
        } catch (NullPointerException | NumberFormatException e) {
            ui.printInvalidTaskNumberMessage(tasks);
        }
    }

    /**
     * Returns a boolean value to indicate whether this command should exit the program.
     *
     * @return false as this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

