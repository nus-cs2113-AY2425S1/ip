package apsea.command;

import apsea.exception.ApseaException;
import apsea.storage.Storage;
import apsea.task.TaskList;
import apsea.ui.Ui;


/**
 * Represents a command to indicate a task as incomplete.
 * This command changes the task from complete status to incomplete status.
 * <code>words</code> represents an array of words user's input.
 */
public class UnmarkTaskCommand extends Command {
    private String[] words;
    private String UNMARK_TASK_ERROR_MESSAGE = "\tSorry, please use the format:\n"
            + "\tunmark [number]";

    public UnmarkTaskCommand(String[] words) {
        this.words = words;
    }

    /**
     * Changes the completion status of a task from done to undone.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if input is not a valid integer or empty.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws ApseaException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (taskList.getTask(taskIndex)).markAsUndone();

            storage.saveData(taskList);
            ui.printUnmarkTask(taskList, taskIndex);
        } catch (Exception e) {
            throw new ApseaException(UNMARK_TASK_ERROR_MESSAGE);
        }
    }
}
