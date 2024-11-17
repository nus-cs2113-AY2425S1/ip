package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.ArrayList;

/**
 * Represents a command to mark a task as done in the task list.
 * Marks the specified task based on the provided index.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking the task at the given index as done.
     * Displays an appropriate message if the task is already marked.
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
            Task taskToBeMarked = taskList.get(this.index);
            if (taskToBeMarked.getStatusIcon().equals("X")) {
                ui.printTaskAlreadyDoneMessage();
            } else {
                taskToBeMarked.markAsDone();
                ui.showMarkTaskMessage(taskToBeMarked);
                storage.save(tasks.getTaskList());
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumberMessage(tasks);
        }
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