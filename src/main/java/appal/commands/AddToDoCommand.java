package appal.commands;

import appal.exception.AppalException;
import appal.exception.EmptyTaskException;
import appal.storage.Storage;
import appal.task.TaskList;
import appal.task.ToDo;
import appal.ui.Ui;

import static appal.common.utils.COMMAND_TODO;
import static appal.common.utils.TASK_INDEX;

public class AddToDoCommand extends Command {
    private String[] inputDetails;
    private boolean isFromUser;

    public AddToDoCommand(String[] inputDetails, boolean isFromUser) {
        super(COMMAND_TODO);
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
        ToDo newToDo = new ToDo(inputDetails[TASK_INDEX]);
        taskList.addTask(newToDo);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }
}
