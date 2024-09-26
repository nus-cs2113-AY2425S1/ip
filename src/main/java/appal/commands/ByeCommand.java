package appal.commands;

import appal.exception.AppalException;
import appal.exception.SaveTasksErrorException;
import appal.storage.Storage;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.BYE_MESSAGE;
import static appal.common.utils.COMMAND_BYE;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(COMMAND_BYE);
    }

    public void saveTasks(Storage storage, TaskList taskList) throws SaveTasksErrorException {
        storage.saveTasksToFile(taskList);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        saveTasks(storage, taskList);
        super.setExit(true);
        ui.printMessage(BYE_MESSAGE);
    }
}
