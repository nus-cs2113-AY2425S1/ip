package apsea.command;

import apsea.exception.ApseaException;
import apsea.storage.Storage;
import apsea.task.Task;
import apsea.task.TaskList;
import apsea.ui.Ui;

/**
 * Represents a command to delete a task to the task list.
 * <code>words</code> represents an array of words user's input.
 */
public class DeleteTaskCommand extends Command {
    private String[] words;
    private String DELETE_FORMAT_ERROR = "\tSorry, please use the format:\n"
            + "\tdelete [number]";

    public DeleteTaskCommand(String[] words) {
        this.words = words;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if input is not an integer, empty or out of range.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws ApseaException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            Task deletedTask = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);

            storage.saveData(taskList);
            ui.printDeleteTask(deletedTask);
            ui.printTotalTaskCount(taskList);
        } catch (Exception e) {
            throw new ApseaException(DELETE_FORMAT_ERROR);
        }
    }
}
