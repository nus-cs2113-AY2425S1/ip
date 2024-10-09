package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list and prints the entire task list to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     * Shows a message if the task list is empty.
     *
     * @param tasks   The TaskList that the command will operate on.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to handle saving and loading tasks (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            ui.printEmptyListMessage();
            return;
        }
        ui.printTaskList(tasks);
    }

    /**
     * Returns a boolean value to indicate whether this command should exit the program.
     *
     * @return false, as this command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}