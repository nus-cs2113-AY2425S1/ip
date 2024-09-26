package appal.commands;

import appal.exception.AppalException;
import appal.exception.EmptyTaskException;
import appal.exception.UnspecifiedEventDurationException;
import appal.storage.Storage;
import appal.task.Event;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.utils.*;

public class AddEventCommand extends Command {
    private String[] inputDetails;
    private boolean isFromUser;

    public AddEventCommand(String[] inputDetails, boolean isFromUser) {
        super(COMMAND_EVENT);
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
        if (inputDetails[FROM_INDEX] == null || inputDetails[TO_INDEX] == null) {
            throw new UnspecifiedEventDurationException();
        }
        Event newEvent = new
                Event(inputDetails[TASK_INDEX], inputDetails[FROM_INDEX], inputDetails[TO_INDEX]);
        taskList.addTask(newEvent);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }
}
