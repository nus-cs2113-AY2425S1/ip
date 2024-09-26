package appal.commands;

import appal.exception.AppalException;
import appal.exception.EmptyTaskException;
import appal.exception.UnspecifiedDeadlineException;
import appal.storage.Storage;
import appal.task.Deadline;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.utils.COMMAND_DEADLINE;
import static appal.common.utils.TASK_INDEX;
import static appal.common.utils.BY_INDEX;

public class AddDeadlineCommand extends Command {
    private String[] inputDetails;
    private boolean isFromUser;

    public AddDeadlineCommand(String[] inputDetails, boolean isFromUser) {
        super(COMMAND_DEADLINE);
        this.inputDetails = inputDetails;
        this.isFromUser = isFromUser;
    }

    public void checkForTask() throws EmptyTaskException {
        if (inputDetails[TASK_INDEX] == null) {
            throw new EmptyTaskException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        checkForTask();
        if (inputDetails[BY_INDEX] == null) {
            throw new UnspecifiedDeadlineException();
        }
        Deadline newDeadline = new Deadline(inputDetails[TASK_INDEX], inputDetails[BY_INDEX]);
        taskList.addTask(newDeadline);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }
}
