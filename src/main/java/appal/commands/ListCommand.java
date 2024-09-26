package appal.commands;

import appal.storage.Storage;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.utils.COMMAND_LIST;

public class ListCommand extends Command {
    public ListCommand() {
        super(COMMAND_LIST);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
    }
}
