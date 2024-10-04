package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.ArrayList;

/**
 * Represents a command to unmark a task in the task list.
 * This command unmarks the specified task based on the provided index.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, unmarking the task at the given index.
     * If the task is already unmarked, it shows an appropriate message.
     * Handles cases where the task list is empty or the index is invalid.
     *
     * @param tasks   The TaskList that contains all tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to handle saving the updated tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            ui.printEmptyListMessage();
            return;
        }
        try {
            Task taskToBeUnmarked = taskList.get(this.index);
            if (taskToBeUnmarked.getStatusIcon().equals(" ")) {
                ui.printTaskAlreadyUnmarkedMessage();
            } else {
                taskToBeUnmarked.unmark();
                ui.showUnmarkTaskMessage(taskToBeUnmarked);
                storage.save(tasks.getTaskList());
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumberMessage(tasks);
        }
    }

    /**
     * Determines whether this command will exit the program.
     *
     * @return false, as unmarking a task does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}