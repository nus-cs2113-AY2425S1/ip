package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list.
 * This command prints the entire task list to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     * If the task list is empty, it shows a message indicating so.
     *
     * @param tasks   The TaskList that the command will operate on.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to handle saving and loading tasks (it is not used in this command).
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
     * Determines whether this command will exit the program.
     *
     * @return false, as listing tasks does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}