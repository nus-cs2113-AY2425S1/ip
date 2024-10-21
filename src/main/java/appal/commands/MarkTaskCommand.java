package appal.commands;

import appal.exception.AppalException;
import appal.exception.InvalidTaskIndexException;
import appal.storage.Storage;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.TASK_DONE_MESSAGE;
import static appal.common.Messages.UNMARK_TASK_MESSAGE;
import static appal.common.Utils.*;

/**
 * MarkTaskCommand class marks or unmarks tasks based on completion of task by user.
 */
public class MarkTaskCommand extends Command {
    private String[] inputDetails;
    private boolean isDone;

    /**
     * Class constructor.
     *
     * @param inputDetails User's input that has been split into
     *                     command type and index of task to be marked parameters
     *                     by {@link appal.parser.Parser#extractInputDetails(String) extractInputDetails} method.
     * @param isDone True if user wants to mark the task as done, false otherwise.
     */
    public MarkTaskCommand(String[] inputDetails, boolean isDone) {
        super(COMMAND_MARK);
        this.inputDetails = inputDetails;
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark or unmark tasks.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if an invalid index is inputted by the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int listIndex = taskId - 1;
            Task taskToMark = taskList.markTask(listIndex, isDone);
            saveTasks(storage, taskList);
            ui.printSeparator();
            if (isDone) {
                System.out.println(TASK_DONE_MESSAGE);
            } else {
                System.out.println(UNMARK_TASK_MESSAGE);
            }
            ui.printOneTask(taskToMark);
            ui.printSeparator();
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
