package appal.commands;

import appal.exception.AppalException;
import appal.exception.InvalidTaskIndexException;
import appal.storage.Storage;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.DELETE_TASK_MESSAGE;
import static appal.common.Utils.COMMAND_MARK;
import static appal.common.Utils.TASK_INDEX;

/**
 * DeleteCommand class deletes a specified task from the task list.
 */
public class DeleteCommand extends Command {
    private String[] inputDetails;

    /**
     * Class constructor.
     *
     * @param inputDetails User's input that has been split into
     *                     command type and index of task to be deleted parameters
     *                     by {@link appal.parser.Parser#extractInputDetails(String) extractInputDetails} method.
     */
    public DeleteCommand(String[] inputDetails) {
        super(COMMAND_MARK);
        this.inputDetails = inputDetails;
    }

    /**
     * Executes the command to delete a task, with a specified index, from the task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if an invalid task index is inputted by the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int indexOfTaskToDelete = taskId - 1;
            ui.printReply(taskList, DELETE_TASK_MESSAGE, indexOfTaskToDelete);
            taskList.removeTask(indexOfTaskToDelete);
            Task.setTotalTasks(Task.getTotalTasks() - 1);
            saveTasks(storage, taskList);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
