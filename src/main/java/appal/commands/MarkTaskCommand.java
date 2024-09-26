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

public class MarkTaskCommand extends Command {
    private String[] inputDetails;
    private boolean isDone;

    public MarkTaskCommand(String[] inputDetails, boolean isDone) {
        super(COMMAND_MARK);
        this.inputDetails = inputDetails;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int listIndex = taskId - 1;
            Task taskToMark = taskList.markTask(listIndex, isDone);
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
