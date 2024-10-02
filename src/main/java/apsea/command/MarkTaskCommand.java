package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.TaskList;
import apsea.ui.Ui;

/**
 * Represents a command to mark a task as complete.
 * This command changes the task from incomplete status to comlete status.
 * <code>words</code> represents an array of words user's input.
 */
public class MarkTaskCommand extends Command {
    private String[] words;
    private String MARK_TASK_ERROR_MESSAGE = "\tSorry, please use the format:\n"
            + "\tmark [number]";

    public MarkTaskCommand(String[] words) {
        this.words = words;
    }

    /**
     * Changes the completed status of a task from undone to done.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if input is not a valid integer or empty.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (taskList.getTask(taskIndex)).markAsDone();

            ui.printMarkTask(taskList, taskIndex);
        } catch (Exception e) {
            throw new ApseaException(MARK_TASK_ERROR_MESSAGE);
        }
    }
}
