package appal.commands;

import appal.exception.AppalException;
import appal.exception.InvalidTaskIndexException;
import appal.storage.Storage;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.DELETE_TASK_MESSAGE;
import static appal.common.utils.COMMAND_MARK;
import static appal.common.utils.TASK_INDEX;

public class DeleteCommand extends Command {
    private String[] inputDetails;

    public DeleteCommand(String[] inputDetails) {
        super(COMMAND_MARK);
        this.inputDetails = inputDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int indexOfTaskToDelete = taskId - 1;
            ui.printReply(taskList, DELETE_TASK_MESSAGE, indexOfTaskToDelete);
            taskList.removeTask(indexOfTaskToDelete);
            Task.setTotalTasks(Task.getTotalTasks() - 1);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
