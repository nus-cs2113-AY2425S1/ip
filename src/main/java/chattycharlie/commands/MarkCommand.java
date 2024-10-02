package chattycharlie.commands;

import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

/**
 * Represents the command to mark a task as completed.
 * This command marks a task at the specified index in the task list.
 */
public class MarkCommand implements Command {
    private int toMarkIndex;

    /**
     * Constructs a <code>MarkCommand</code> using the provided input line.
     * Extracts the task index that needs to be marked as completed.
     *
     * @param line the input line containing the command and task index to mark as completed.
     * @throws NumberFormatException if the index provided is not a valid integer.
     */
    public MarkCommand(String line) {
        String toMarkIndex = line.substring(4).trim();
        this.toMarkIndex = Integer.parseInt(toMarkIndex) - 1;
    }

    /**
     * Executes the <code>MarkCommand</code> by marking the task at the specified index
     * in the task list and displaying the updated task to the user.
     *
     * @param taskList the list of tasks where a task will be marked as completed.
     * @param ui the user interface for displaying the updated task.
     * @param storage the storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(toMarkIndex);
        ui.displayTask(taskList.getTask(toMarkIndex));
    }
}
