package chattycharlie.commands;

import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

/**
 * Represents the command to unmark a task as incomplete.
 * This command unmarks a task at the specified index in the task list.
 */
public class UnmarkCommand implements Command {
    private int toUnmarkIndex;

    /**
     * Constructs an <code>UnmarkCommand</code> using the provided input line.
     * Extracts the task index that needs to be unmarked as incomplete.
     *
     * @param line the input line containing the command and task index to unmark.
     * @throws NumberFormatException if the index provided is not a valid integer.
     */
    public UnmarkCommand(String line) {
        String toUnmarkIndex = line.substring(6).trim();
        this.toUnmarkIndex = Integer.parseInt(toUnmarkIndex) - 1;
    }

    /**
     * Executes the <code>UnmarkCommand</code> by unmarking the task at the specified index
     * in the task list and displaying the updated task to the user.
     *
     * @param taskList the list of tasks where a task will be unmarked as incomplete.
     * @param ui the user interface for displaying the updated task.
     * @param storage the storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(toUnmarkIndex);
        ui.displayTask(taskList.getTask(toUnmarkIndex));
    }
}
